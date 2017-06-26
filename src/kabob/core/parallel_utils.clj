(ns kabob.core.parallel-utils
  (use kr.core.kb
       kr.core.rdf
       kr.core.sparql
       kr.sesame.writer-kb
       kr.core.utils

       clojure.stacktrace
       clojure.pprint)
  (import java.util.concurrent.ThreadPoolExecutor
          java.util.concurrent.TimeUnit
          java.util.concurrent.BlockingQueue
          java.util.concurrent.ArrayBlockingQueue
          java.util.concurrent.ThreadPoolExecutor$CallerRunsPolicy))

;;; --------------------------------------------------------
;;; constants
;;; --------------------------------------------------------

;;assuming a string is about 1kb, and we want to allocate 1.5gb
;; 1gb is 1024 * 1024 in kb (moving to mb then gb)
;;(def ^:dynamic *lu-cache-size* (int (* 1.5 1024 1024)))
;;(def ^:dynamic *lu-cache-size* (int 32))

(def ^:dynamic *work-queue-single-threaded* false)

(def ^:dynamic *parallel-query-logging*           true)
(def ^:dynamic *parallel-query-logging-frequency* 250000)
(def ^:dynamic *parallel-query-gc-frequency*      10000)

(def ^:dynamic *parallel-query-expected-count*    true)

;;; --------------------------------------------------------
;;; helpers
;;; --------------------------------------------------------

;; (defn new-cache []
;;   (atom (hash-set)))
;; ;;  (atom (basic-cache-factory {})))
;; ;;  (atom (lu-cache-factory {} :threshold *lu-cache-size*)))

;; ;; (defn hit-or-miss [cache item]
;; ;;   (if (has? cache item)
;; ;;     (hit cache item)
;; ;;     (miss cache item nil)))

;; ;; (defn remember [cache item]
;; ;;   (let [seen (@cache item)]


(defmacro with-work-queue [wq-fn & body]
  ;;the # syntax is like (let [threads (gensym ... in lisp
  `(let [threads# (if *work-queue-single-threaded*
                    0
                    (+ 2 (.availableProcessors (Runtime/getRuntime))))
         wq# (if *work-queue-single-threaded*
               nil
               (ThreadPoolExecutor. 10 10 
                                    20 TimeUnit/SECONDS
                                    (ArrayBlockingQueue. 100)
                                    (ThreadPoolExecutor$CallerRunsPolicy.)))
         ~wq-fn (with-meta (fn [work-thunk#]
                             (if *work-queue-single-threaded*
                               (work-thunk#) ;caller runs
                               (.execute wq# work-thunk#))) ;queue
                  {:work-queue wq#
                   :threads threads#})]
     (println "Creating work queue with " threads# "threads.")
     (let [result# (try ;save and return whatever body does
                     ~@body ;call (.execute wq (fn [])) in body
                     (finally (when (not *work-queue-single-threaded*)
                                (.shutdown wq#))))]
       ;;wait for orderly shutdown
       (when (not *work-queue-single-threaded*)
         (while (not (.awaitTermination wq# 20 TimeUnit/SECONDS))
           nil)) ;(println "waiting again"))
       result#)))


;;; --------------------------------------------------------
;;; compute record set diffs
;;; --------------------------------------------------------

;;process-new-record [old-kb new-kb rs-diff old-rs record key-field-set]
(defn blocking-write-triples [write-agent triples]
  ;; block this thread until all it's previous requests have been processed
  ;; arguably this should go at the end instead? but that would slow it down
  ;;   unnecessarily, this sacrifices a little memory, but shouldn't slow
  ;;   things down unduely

  ;;moved to after
  ;;(await write-agent)
  (send-off write-agent
            (fn [kb statements]
              (dorun
               (map (partial add! kb) statements))
              kb)
            triples)
  ;;witing here will prevent unintended concequences of work still going
  ;;  while file/kb is closing
  (await write-agent))


;;EXAMPLE:
;; (let [write-agent (agent output-kb)]
;;   (try
;;     (with-work-queue work
;;       (query-visit new-kb
;;                    (fn [bindings]
;;                      (work ;throw thunk into the work queue
;;                       (fn []
;;                         (let [record (bindings '?/record)
;;                               new-triples (and record
;;                                                (process-new-record old-kb
;;                                                                   new-kb-conn
;;                                                                    rs-diff
;;                                                                    old-rs
;;                                                                    record
;;                                                                    key-fields
;;                                                                    nil))]
;;                           (when (not (empty? new-triples))
;;                             (blocking-write-triples write-agent
;;                                                     new-triples))))))
;;                    `((~new-rs obo/BFO_0000051 ?/record))))
;;     (finally (close new-kb-conn)))))

;;; --------------------------------------------------------
;;; parallel triple production
;;; --------------------------------------------------------

(defn logging-query-visit [kb query-pat fn-of-bindings ]
  (let [count (atom 0)
        t (atom (.getTime (java.util.Date.)))]
    (try
      (when *parallel-query-expected-count*
        (println "expected count:")
        (time
         (println (query-count kb query-pat))))
      (query-visit kb
                   (fn [bindings]
                     (swap! count inc)
                     (when (= 0 (mod @count *parallel-query-gc-frequency*))
                       (System/gc))
                     (when (and *parallel-query-logging*
                                (= 0 (mod @count
                                          *parallel-query-logging-frequency*)))
                       (let [new-t (.getTime (java.util.Date.))]
                         (println @count " in " (- new-t @t) "ms")
                         (reset! t new-t)))
                     (fn-of-bindings bindings))
                   query-pat)
      (catch Exception e (print-cause-trace e))
      (finally (when (and *parallel-query-logging*
                          (println "final count: " @count)))))))

(defn parallel-query-visit [kb query-pat fn-of-bindings ]
  (with-work-queue work
    (logging-query-visit kb
                         query-pat
                         (fn [bindings]
                           (work (fn []
                                   (fn-of-bindings bindings)))))))
  
(defn parallel-triple-creation [input-kb output-kb query-pat fn-of-bindings]
  (let [write-agent (agent output-kb)]
    (parallel-query-visit input-kb
                          query-pat
                          (fn [bindings]
                            (blocking-write-triples write-agent
                                                    (fn-of-bindings bindings))))
    ;; the work queue make sure that it is shut down before exiting, so
    ;;  all other threads should be done by now, and then makes sure that
    ;;  this thread is caught up with the write agent
    (await write-agent)))





(defn logging-query-visit-sparql [kb sparql fn-of-bindings ]
  (let [count (atom 0)
        t (atom (.getTime (java.util.Date.)))]
    (try
      ;; (when *parallel-query-expected-count*
      ;;   (println "expected count:")
      ;;   (time
      ;;    (println (query-count kb query-pat))))
      (sparql-visit kb
                    (fn [bindings]
                      (swap! count inc)
                      (when (= 0 (mod @count *parallel-query-gc-frequency*))
                        (System/gc))
                      (when (and *parallel-query-logging*
                                 (= 0 (mod @count
                                           *parallel-query-logging-frequency*)))
                        (let [new-t (.getTime (java.util.Date.))]
                          (println @count " in " (- new-t @t) "ms")
                          (reset! t new-t)))
                      (fn-of-bindings bindings))
                    sparql)
      (catch Exception e (print-cause-trace e))
      (finally (when (and *parallel-query-logging*
                          (println "final count: " @count)))))))

(defn parallel-query-visit-sparql [kb sparql fn-of-bindings ]
  (with-work-queue work
    (logging-query-visit-sparql kb
                                sparql
                                (fn [bindings]
                                  (work (fn []
                                          (fn-of-bindings bindings)))))))
  
(defn parallel-triple-creation-sparql [input-kb output-kb sparql
                                       fn-of-bindings]
  (let [write-agent (agent output-kb)]
    (parallel-query-visit-sparql
     input-kb
     sparql
     (fn [bindings]
       (blocking-write-triples write-agent
                               (fn-of-bindings bindings))))
    ;; the work queue make sure that it is shut down before exiting, so
    ;;  all other threads should be done by now, and then makes sure that
    ;;  this thread is caught up with the write agent
    (await write-agent)))




;; (defn nonparallel-triple-creation-sparql [input-kb output-kb sparql
;;                                        fn-of-bindings]
;;   (parallel-query-visit-sparql
;;    input-kb
;;    sparql
;;    (fn [bindings]
;;      (blocking-write-triples write-agent
;;                              (fn-of-bindings bindings))))
;;   ;; the work queue make sure that it is shut down before exiting, so
;;     ;;  all other threads should be done by now, and then makes sure that
;;   ;;  this thread is caught up with the write agent
;;   (await write-agent)))

;;; --------------------------------------------------------
;;; testing
;;; --------------------------------------------------------

;; this is a test procedure to make sure the work queue waits
(defn test-work-queue-dropping-work [a]
    (with-work-queue work-queue
      (dotimes [n 20]
        (.execute work-queue (fn []
                               (Thread/sleep 5000)
                               (send-off a (fn [v] (+ 1 v)))))))
    @a)

;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
