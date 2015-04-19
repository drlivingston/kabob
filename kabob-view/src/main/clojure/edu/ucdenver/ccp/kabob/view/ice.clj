(ns edu.ucdenver.ccp.kabob.view.ice
 (use compojure.core
      [hiccup core page element]

      edu.ucdenver.ccp.kabob.ice-util
      
      edu.ucdenver.ccp.kabob.view.utils
      edu.ucdenver.ccp.kabob.kr.entities
      edu.ucdenver.ccp.kabob.view.record))



(defn value-view [value]
  (kabob-page (str "records with: " value)
    (let [val (read-string value)
          records (records-for val)]
      (prn records)
      (html [:br][:br]
       (map (fn [r]
              (html (record-link r) [:br][:br]))
            records)))))


      ;; (html [:div "IDs:" [:br]
      ;;        (doall
      ;;         (map (fn [id] (html (str id) [:br]))
      ;;              ids))]
      ;;       [:br][:br]
      ;;       (render-records-with-values ids)))))


(defroutes ice-routes
  (GET "/value"  {params :params} (value-view (:value params)))
  (GET "/record" {params :params} (record-view (:id params))))