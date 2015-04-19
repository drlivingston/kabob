(ns edu.ucdenver.ccp.kabob.query.data-io
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.utils

       edu.ucdenver.ccp.kabob.query.util
       edu.ucdenver.ccp.kabob.query.entities
       clojure.pprint
       ;;clojure.set
       )
  (require [clojure.java.io :as io]
           [clojure.data.csv :as csv]
           loom.graph
           loom.attr
           loom.io))


;;; --------------------------------------------------------
;;; input
;;; --------------------------------------------------------


;; given an inverted binding index expand the keys into entities

(defn read-csv-file [file]
  (with-open [in-file (io/reader file)]
    (doall
     (csv/read-csv in-file))))

;;zero based
(defn get-column [col csv-data]
  (map (fn [line]
         (nth line col))
       csv-data))


;;; --------------------------------------------------------
;;; writing files
;;; --------------------------------------------------------

(defn write-csv [file csv] 
  (with-open [out-file (io/writer file)]
    (csv/write-csv out-file csv)))

(defn write-sif [data file]
  )


(defn write-loom-png [data file]
  )

;;; --------------------------------------------------------
;;; data generation
;;; --------------------------------------------------------


(defn backgrounds-csv [two-list-backgrounds]
  (cons ["entity" "list1-count" "list2-count" "global-count" "fold-change"
         "ratio-list1" "ratio-list2" "ratio-list1-global" "ratio-list2-global"
         "unique" "tags" "list1-entities"]
        (sort-by (fn [x] (if (nth x 9) 1 0)) ;unique
                 >
                 ;;(sort-by (fn [x] (nth x 4)) ;fold change
                 (sort-by (fn [x] (nth x 7)) ;ratio list1-global
                          ;;second
                          >
                          (map (fn [data]
                                 [(or (:label (get data :entity {}))
                                      (:key data))
                                  (:count-list1 data)
                                  (:count-list2 data)
                                  (:count-global data)
                                  
                                  ;;should never be zero bottom / -1
                                  (if (= 0 (:ratio-list2 data))
                                    -1
                                    (/ (* 1.0 (:ratio-list1 data))
                                       (* 1.0 (:ratio-list2 data))))
                                  
                                  (* 1.0 (:ratio-list1 data))
                                  (* 1.0 (:ratio-list2 data))
                                  (* 1.0 (:ratio-list1-global data))
                                  (* 1.0 (:ratio-list2-global data))
                                  (:unique data)
                                  (apply str
                                         (interpose " "
                                                    (sort
                                                     (:tags (:entity data)))))
                                  (apply str
                                         (interpose " "
                                           (sort
                                            (map (fn [e]
                                                   (str (:label e)))
                                                 (:list1-entities data)))))])
                               two-list-backgrounds)))))

;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
