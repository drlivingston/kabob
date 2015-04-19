(ns edu.ucdenver.ccp.kabob.kr.record-diff
  (use clojure.set
       edu.ucdenver.ccp.kr.clj-ify
       edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kabob.rule

       edu.ucdenver.ccp.kr.sesame.writer-kb

       edu.ucdenver.ccp.utils

       edu.ucdenver.ccp.kabob.parallel-utils

       edu.ucdenver.ccp.kabob.util.hashing

       clojure.core.cache
       clojure.pprint)
  ;; (import java.security.MessageDigest
  ;;         java.math.BigInteger
  ;;         org.apache.commons.codec.binary.Base64
          
  ;;         java.util.concurrent.ThreadPoolExecutor
  ;;         java.util.concurrent.TimeUnit
  ;;         java.util.concurrent.BlockingQueue
  ;;         java.util.concurrent.ArrayBlockingQueue
  ;;         java.util.concurrent.ThreadPoolExecutor$CallerRunsPolicy
  ;;         )
  )

;;; --------------------------------------------------------
;;; constants
;;; --------------------------------------------------------

;;; --------------------------------------------------------
;;; helpers
;;; --------------------------------------------------------

(defmacro ppret [form]
  `(let [r# ~form]
     (pprint r#)
     r#))

(defn new-cache []
  (atom (hash-set)))
;;  (atom (basic-cache-factory {})))
;;  (atom (lu-cache-factory {} :threshold *lu-cache-size*)))

;; (defn hit-or-miss [cache item]
;;   (if (has? cache item)
;;     (hit cache item)
;;     (miss cache item nil)))

;; (defn remember [cache item]
;;   (let [seen (@cache item)]
;;     (when (not seen)
;;       (swap! cache conj item))
;;     seen))

;; (defn hit-or-miss [cache item]
;;   (if (has? cache item)
;;     (hit cache item)
;;     (miss cache item nil)))

;; (defn remember [cache item]
;;   (let [seen (has? @cache item)]
;;     (swap! cache hit-or-miss item)
;;     seen))
    

;; (defmacro with-work-queue [wq & body]
;;   ;;the # syntax is like (let [threads (gensym ... in lisp
;;   `(let [threads# (+ 2 (.availableProcessors (Runtime/getRuntime)))
;;          ~wq (ThreadPoolExecutor. threads# threads#
;;                                   20 TimeUnit/SECONDS
;;                                   (ArrayBlockingQueue. (* threads# 10))
;;                                   (ThreadPoolExecutor$CallerRunsPolicy.))]

(defn pat-triples [kb pat]
  (apply concat
         (query-template kb pat pat)))


;;; --------------------------------------------------------
;;; record fields and values
;;; --------------------------------------------------------

;; (defn record-fields-and-values [kb record]
;;   (query-template kb '(?/template ?/value)
;;                   `((~record obo/has_part _/fv)
;;                     (_/fv kiao/hasTemplate ?/template)
;;                     (_/fv obo/IAO_0000219 ?/value))))

(defn has-fv? [kb fv]
   (ask-rdf kb fv 'rdf/type 'kiao/FieldValue))
;;(ask kb `((~fv rdf/type kiao/FieldValue)))))

(defn fv-data [kb fv]
  (query-template kb '(?/template ?/value)
                  `((~fv kiao/hasTemplate ?/template)
                    (~fv obo/IAO_0000219 ?/value))))

(defn record-fv [kb record]
  (query-template kb '?/fv
                  `((~record obo/has_part ?/fv))))

(defn record-fv-and-templates [kb record]
  (query-template kb '(?/fv ?/template)
                  `((~record obo/has_part ?/fv)
                    (?/fv kiao/hasTemplate ?/template))))

(defn record-set-schema [kb rs]
  (first
   (query-template kb '?/template
                   `((~rs kiao/hasTemplate ?/template)))))


(defn clone-fv [kb fv]
  (binding [*use-inference* false]
    (query-rdf kb fv nil nil)))
;;   (pat-triples kb `(
;;                     (~fv obo/IAO_0000219 ?/value)
;;                     (~fv kiao/hasTemplate ?/template)
;;                     (~fv rdf/type kiao/FieldValue)
;; )))

  ;; ;;(println "$$$$$$$$$$$$$$")
  ;; (let [pat (ppret`((~fv rdf/type kiao/FieldValue)
  ;;                         (~fv kiao/hasTemplate ?/template)
  ;;                         (~fv obo/IAO_0000219 ?/value)))]
  ;; (ppret (pat-triples kb pat ))))
  ;; (let [triple-pat `((~fv rdf/type kiao/FieldValue)
  ;;                    (~fv kiao/hasTemplate ?/template)
  ;;                    (~fv obo/IAO_0000219 ?/value))]
;;   (query-template kb triple-pat triple-pat)))

(defn clone-fv-if-necessary [source-kb target-kb fv-cache fv]
  (if (or (has-fv? target-kb fv)
          ;(remember fv-cache fv)
          nil)
    '()
    (clone-fv source-kb fv)))


;;TODO WARNING: this will over clone fvs every time the missing one appears
(defn clone-record [source-kb target-kb fv-cache rec]
  ;; (println "***foo: "
  ;;           (query-template source-kb '?/fv `((~rec obo/has_part ?/fv))))
;;  (concat
  (binding [*use-inference* false]
   (query-rdf source-kb rec nil nil))
   ;; (mapcat (fn [fv]
   ;;           (clone-fv-if-necessary source-kb target-kb fv-cache fv))
   ;;         (query-template source-kb '?/fv `((~rec obo/has_part ?/fv))))))

)

;;; --------------------------------------------------------
;;; fv signature
;;; --------------------------------------------------------

(defn sig-uri [ns sig]
  (symbol ns (str "S_" sig)))

(defn fv-signature [f-v-list]
  (sha-1 (flatten
          (sort (map (partial mapv str) ;mapv returns a vector
                     f-v-list)))))

(defn fv-sig [kb ns f-v-list]
  (sig-uri ns (fv-signature (map (partial map (partial to-hashing-string kb))
                                          f-v-list))))

;;; --------------------------------------------------------
;;; record signature
;;; --------------------------------------------------------

;; (defn record-signature [kb record]
;;   (query-template kb '?/sig
;;                   `((~record kiao/hasSignature ?/sig))))
  
;; (defn record-signature-fields [kb record-type]
;;   (query-template kb '?/f
;;                   `((~record-type kiao/hasSignatureField ?/f))))

;; ;;this would be better as returning a function of record
;; (defn record-signature-f-and-v [kb sig-fields record]
;;   (query-template kb '(?/template ?/value)
;;                   `((~record obo/has_part _/fv)
;;                     (_/fv kiao/hasTemplate ?/template)
;;                     (_/fv obo/IAO_0000219 ?/value)
;;                     (:or
;;                      ~@(map (fn [f]
;;                               `(:sameTerm ?/template ~f))
;;                             sig-fields)))))
  
;; (defn compute-record-signature [kb ns sig-fields record]
;;   (fv-sig kb ns (record-signature-f-and-v kb sig-fields record)))

;;; --------------------------------------------------------
;;; record keys
;;; --------------------------------------------------------

;; (defn record-key-fields [kb record-type]
;;   (set
;;    (query-template kb '?/f
;;                    `((~record-type kiao/hasKeyField ?/f)))))

;; change: schema not record type have key fields

;; (defn record-key-fields [kb schema]
;;   (set
;;    (query-template kb '?/f
;;                    `((~schema kiao/hasKeyField ?/f)))))

;; if all fields are keys or there are no keys return nil
;;   single record diff is as a whole
(defn record-key-fields [kb schema]
  (let [keys (set
              (query-template kb '?/f
                              `((~schema kiao/hasKeyField ?/f))))]
    (if (empty? keys)
      nil
      (if (every? keys (query-template kb '?/f
                                       `((~schema obo/has_part ?/f))))
        nil
        keys))))

(defn record-key [fv-and-templates key-field-set]
  ;;(sort
  (map first
       (remove (fn [[fv t]] (not (key-field-set t)))
               fv-and-templates)));)


;;; matching record
;;; --------------------------------------------------------

(defn record-with-key-query [key & [record-set]]
  (concat 
   (if record-set
     `((~record-set obo/has_part ?/record))
     '())
   (map (fn [fv]
          `(?/record obo/has_part ~fv))
        key)))

(defn records-with-key [kb key & [record-set]]
  (let [query-pat (record-with-key-query key record-set)]
    (query-template kb '?/record query-pat)))

(defn record-with-key? [kb key & [record-set]]
  (let [query-pat (record-with-key-query key record-set)]
    (ask kb query-pat)))

(defn records-and-fv-with-key [kb key & [record-set]]
  (let [query-pat (concat (record-with-key-query key record-set)
                          '((?/record obo/has_part ?/fv)))]
    (query-template kb '(?/record ?/fv) query-pat)))


;;; --------------------------------------------------------
;;; record diff
;;; --------------------------------------------------------

(defn diff-uri [ns prefix & parts]
  (symbol ns
          (str prefix
               (apply sha-1 parts))))

(defn rs-diff-uri [ns from-record-set to-record-set]
  (diff-uri ns "rsdiff" from-record-set to-record-set))

(defn r-diff-uri [ns from-record to-record]
  (diff-uri ns "rdiff" from-record to-record))

  
(defn compute-part-diff [diff from from-parts to to-parts part-cache]
  (let [from-set (set from-parts)
        to-set   (set to-parts)
        from-only (difference from-set to-set)
        to-only   (difference to-set from-set)]
    (concat `((~diff rdf/type kiao/Diff)
              (~diff kiao/diffFrom ~from)
              (~diff kiao/diffTo ~to))
            (map (fn [part] `(~diff kiao/removePart ~part))
                 from-only)
            (map (fn [part] `(~diff kiao/addPart ~part))
                 to-only))))

(defn seperate-r-fv [r-to-fv-pairs]
  (let [r-to-fv (reduce (fn [r-to-fv-map [r fv]]
                          (assoc r-to-fv-map r
                                 (conj (get r-to-fv-map r []) fv)))
                        {}
                        r-to-fv-pairs)
        num (count r-to-fv)]
    (when (not (= 1 num))
      (println "WARNING: too many records for key: " count "\n" r-to-fv))
    (first r-to-fv)))


;;add fvs
(defn clone-dropped-fvs [source-kb target-kb fv-cache fvs]
  (mapcat (partial clone-fv-if-necessary source-kb target-kb fv-cache) fvs))
  
(defn process-new-record [old-kb new-kb rs-diff old-rs
                          record key-field-set fv-cache]
  ;;(dorun (map pprint (list rs-diff old-rs record key-field-set)))
  ;;first see if the identical record existed before
  (if (ask-rdf old-kb old-rs 'obo/has_part record)
    nil ;do nothing ;;if this is more than one we should complain loudly?
    ;;otherwise check for a record with matching key fields
    (if (not key-field-set) ;; there are no keys drop the record
      `((~rs-diff kiao/removePart ~record))
      ;;look for it's match
      (let [new-fv-t (record-fv-and-templates new-kb record)
            new-key  (record-key new-fv-t key-field-set)
            old-r-fv (records-and-fv-with-key old-kb new-key old-rs)]
        (cons
         `(~rs-diff kiao/removePart ~record)
         (if (empty? old-r-fv)
           '() ;; no matching key so no diff
           (let [new-fields (map first new-fv-t)
                 [old-record old-fields] (seperate-r-fv old-r-fv)
                 rdiff-uri (r-diff-uri (namespace rs-diff)
                                       (sym-to-long-name old-kb old-record)
                                       (sym-to-long-name new-kb record))]
             (concat `((~rs-diff kiao/addPart ~old-record)
                       (~record  dcterms/replaces ~old-record))
                     (compute-part-diff rdiff-uri
                                        record     new-fields
                                        old-record old-fields
                                        fv-cache)
                     ;; (clone-dropped-fvs old-kb new-kb fv-cache
                     ;;                    (difference (set old-fields)
                     ;;                                (set new-fields)))
                     ))))))))


(defn process-old-record [old-kb new-kb rs-diff new-rs
                          record key-field-set fv-cache]
  ;;(dorun (map pprint (list rs-diff old-rs record key-field-set)))
  ;;first see if the identical record existed before
  (if (ask-rdf new-kb new-rs 'obo/has_part record)
    nil ;do nothing ;;if this is more than one we should complain loudly?
    ;;otherwise check for a record with matching key fields
    (if (and key-field-set
             (let [old-fv-t (record-fv-and-templates old-kb record)
                   old-key  (record-key old-fv-t key-field-set)]
               (record-with-key? new-kb old-key new-rs)))
      '()                                     ;it exists do nothing
      (cons `(~rs-diff kiao/addPart ~record)  ;else add the diff
            (clone-record old-kb new-kb fv-cache record)))))

;;; --------------------------------------------------------
;;; compute record set diffs
;;; --------------------------------------------------------

(defn new-kb-record-diff [old-kb
                          new-kb
                          rs-diff
                          old-rs
                          new-rs
                          ;record
                          key-fields
                          write-agent]
  (let [new-kb-conn (connection new-kb true)]
    (try
      (with-work-queue work
        (query-visit new-kb
                     (fn [bindings]
                       (work ;throw thunk into the work queue
                        (fn []
                          (let [record (bindings '?/record)
                                new-triples (and record
                                                 (process-new-record old-kb
                                                                     new-kb-conn
                                                                     rs-diff
                                                                     old-rs
                                                                     record
                                                                     key-fields
                                                                     nil))]
                            (when (not (empty? new-triples))
                              (blocking-write-triples write-agent
                                                      new-triples))))))
                     `((~new-rs obo/has_part ?/record))))
      (finally (close new-kb-conn)))))

(defn old-kb-record-diff [old-kb
                          new-kb
                          rs-diff
                          old-rs
                          new-rs
                          ;record
                          key-fields
                          write-agent]
  (let [old-kb-conn (connection old-kb true)]
    (try
      (with-work-queue work
        (query-visit old-kb
                     (fn [bindings]
                       (work ;throw thunk into the work queue
                        (fn []
                          (let [record (bindings '?/record)
                                new-triples (and record
                                                 (process-old-record old-kb-conn
                                                                     new-kb
                                                                     rs-diff
                                                                     new-rs
                                                                     record
                                                                     key-fields
                                                                     nil))]
                            (when (not (empty? new-triples))
                              (blocking-write-triples write-agent
                                                      new-triples))))))
                     `((~old-rs obo/has_part ?/record))))
      (finally (close old-kb-conn)))))


;;this really should take two record sets not a type
(defn generate-record-diffs [old-kb new-kb output-kb old-rs new-rs]
  (let [schema      (record-set-schema new-kb new-rs)
        ns          (namespace schema)
        rs-diff     (rs-diff-uri ns new-rs old-rs)
        ;;intentionally blocking keys to test eg diff size
        key-fields  (record-key-fields new-kb schema)
        write-agent (agent output-kb)
        fv-cache nil ;(new-cache)
        ] ; agent to synchronize output

    (println "Key Fields: " key-fields)
 
    (println "old kb scan:")
    (time
     (let [old-kb-conn (connection old-kb true)
           new-kb-conn (connection new-kb true)]
       (try
         (old-kb-record-diff old-kb-conn
                             new-kb-conn
                             rs-diff
                             old-rs
                             new-rs
                             key-fields
                             write-agent)
       (finally (close old-kb-conn)
                (close new-kb-conn)))))
    ;;need to wait on the write-agent?
    (await write-agent)

    (println "new kb scan:")
    (time
     (let [old-kb-conn (connection old-kb true)
           new-kb-conn (connection new-kb true)]
       (try
         (new-kb-record-diff old-kb-conn
                             new-kb-conn
                             rs-diff
                             old-rs
                             new-rs
                             key-fields
                             write-agent)
         (finally (close old-kb-conn)
                  (close new-kb-conn)))))
    (await write-agent) ;let the writer catch up

    ))



(defn preserve-old-fvs [old-kb new-kb output-kb field]
  (let [ns          (namespace field)
        old-kb-conn (connection old-kb true) ;should each one get their own?
        new-kb-conn (connection new-kb true) ;should each one get their own?
        write-agent (agent output-kb)] ; agent to synchronize output
    (try
      (with-work-queue work
        (query-visit old-kb
                     (fn [bindings]
                       (work ;throw thunk into the work queue
                        (fn []
                          (let [fv (bindings '?/fv)
                                new-triples
                                (and fv
                                     (clone-fv-if-necessary old-kb-conn
                                                            new-kb-conn
                                                            nil fv))]
                            (when (not (empty? new-triples))
                              (blocking-write-triples write-agent
                                                      new-triples))))))
                     `((?/fv kiao/hasTemplate ~field))))
      ;;need to wait on the write-agent?
      (await write-agent)
      (finally (close old-kb-conn)
               (close new-kb-conn)))))


;;; --------------------------------------------------------
;;; bootstrap temp eg
;;; --------------------------------------------------------


;; (defn eg-bootstrap-triples [kb]
;;   (dorun
;;    (map (partial add kb)
;;         '((iaoeg/egRecord rdfs/subClassOf kiao/Record)
;;           (iaoeg/egRecord kiao/hasSignatureField
;;                           iaoeg/EntrezGeneInfoFileData_geneIDDataField1)))))

;;TODO this should be on the schema no the record
;; (defn eg-key-field-triples [kb]
;;   (dorun
;;    (map (partial add kb)
;;         '((iaoeg/egRecord kiao/hasKeyField
;;                           iaoeg/EntrezGeneInfoFileData_geneIDDataField1)))))


;; (defn eg-sig-triples [ice-kb]
;;   (let [output-kb
;;         (initialize-ns-mappings
;;          (open (new-sesame-writer-kb
;;                 "/temp/kabob/eg2011-sig-triples.nt"))
;;          ice-kb)]
;;     (try
;;       (generate-record-signatures-for-record-type ice-kb
;;                                                   output-kb
;;                                                   'iaoeg/egRecord)
;;       (finally (close output-kb)))))


;;still doesn't account for old records that were dropped
;; (defn eg-diff-triples [old-kb new-kb]
;;   (let [output-kb
;;         (initialize-ns-mappings
;;          (open (new-sesame-writer-kb
;;                 "/temp/kabob/eg201102-201208-diff-triples.nt"))
;;          new-kb)]
;;     (try
;;       (generate-record-diffs old-kb
;;                              new-kb
;;                              output-kb
;;                              'iaoeg/egEntrezGeneInfoFileDataDataSet20110209
;;                              'iaoeg/egEntrezGeneInfoFileDataDataSet20120815)
;;                              ;'iaoeg/egRecord)
;;       (catch Exception e (println "EXCEPTION: " e))
;;       (finally (close output-kb)))))


;; (defn eg-diff-triples-current [old-kb new-kb]
;;   (let [output-kb
;;         (initialize-ns-mappings
;;          (open (new-sesame-writer-kb
;;                 "/temp/kabob/eg201208-201209-diff-triples.nt"))
;;          new-kb)]
;;     (try
;;       (generate-record-diffs old-kb
;;                              new-kb
;;                              output-kb
;;                              'iaoeg/egEntrezGeneInfoFileDataDataSet20120815
;;                              'iaoeg/egEntrezGeneInfoFileDataDataSet20120925)
;;                              ;'iaoeg/egRecord)
;;       (catch Exception e (println "EXCEPTION: " e))
;;       (finally (close output-kb)))))


;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
