(ns kabob.build.live-test-utils
  (use clojure.test
       edu.ucdenver.ccp.utils
       edu.ucdenver.ccp.kr.rule
       edu.ucdenver.ccp.kr.forward-rule
       clojure.pprint))

;;; --------------------------------------------------------
;;; constansts
;;; --------------------------------------------------------

;;(defonce ^:dynamic *rcon* nil)

;; (def id-typing-rules-root "edu/ucdenver/ccp/kabob/build/id_typing/")
;; (def id-mapping-rules-root "edu/ucdenver/ccp/kabob/build/id_mapping/")
;; (def entity-typing-rules-root "edu/ucdenver/ccp/kabob/build/entity_typing/")


;;; --------------------------------------------------------
;;; tests
;;; --------------------------------------------------------

;; (defn test-forward-safe-rules [rules]
;;     (is (< 0 (count rules)))
;;     (doseq [r rules]
;;       (is (not (nil? (meta r)))))
;;     (is (= 0 (count (bad-rules forward-safe? rules)))))

(defn count-forward-rules [source-kb rules]
  (map (fn [rule]
         (apply vector rule
                (time-multiple-value
                 (count-forward-rule source-kb rule))))
       rules))

(defn sort-rule-counts [results]
  (sort-by second > results))
  ;; (sort (fn [x y]
  ;;         (> (second x) (second y)))
  ;;       results))



(defn rule-count-report [results]
  (doseq [r results]
    (pprint (second r) "#" (nth 2 results) "ms" (:name (first r)))
    (when (= 0 (second r))
      (pprint "potential non-triggering rule:")
      (pprint (meta (first r)))
      (pprint (first r)))))


(defn ask-all-rules [kb rules]
  (remove (partial ask-forward-rule kb) rules))

                     

;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
