(ns edu.ucdenver.ccp.kabob.view.record
 (use compojure.core
      [hiccup core page]
      edu.ucdenver.ccp.kabob.view.utils
      edu.ucdenver.ccp.kabob.ice-util))

(defn fv-table [fvs]
  ;;(prn fvs)
  (html
   [:table {:class "data-table"}
    [:thead
     [:tr
      [:th "field"]
      [:th "value"]]]
    [:tbody
      (map (fn [[f v]]
             (html
              [:tr
               [:td f]
               [:td (value-link v)]]))
           (sort (fn [[f1 v1] [f2 v2]]
                   (if (= f1 f2)
                     (compare (str v1) (str v2))
                     (compare (str f1) (str f2))))
                 fvs))]]))
;;           fvs)]]))
;;(sort fvs))]]))

(defn render-record [record]
  (let [fvs (record-field-values record)]
    (html
     [:div {:class (str "record " record)}
      [:h3 (prn-str record)]
      (fv-table fvs)])))

(defn render-records [records]
  (doall
   (map (fn [record]
          ;;(prn record)
          (html
           (render-record record)
           [:br] [:br]))
        records)))

(defn records-with-values [values]
  (doall
   (distinct
    (mapcat (fn [value]
              ;;(prn value)
              (records-for value))
            values))))

(defn render-records-with-values [values]
  (render-records (records-with-values values)))


;;TODO validate symbols!!
(defn record-view [record]
  (kabob-page record
              (let [rec (read-string record)]
                (render-record rec))))



