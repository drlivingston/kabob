(ns edu.ucdenver.ccp.kabob.view.entities
 (use compojure.core
      [hiccup core page]

      edu.ucdenver.ccp.kr.kb
      
      edu.ucdenver.ccp.kabob.view.kb
      edu.ucdenver.ccp.kabob.view.utils
      edu.ucdenver.ccp.kabob.kr.entities
      edu.ucdenver.ccp.kabob.view.record

      edu.ucdenver.ccp.kabob.view.entities.dna))


;; (defn entity-view [id-string]
;;   (kabob-page id-string
;;     (let [entity (read-string id-string)
;;           ids (ice-denoting (view-kb) entity)]
;;       (doall (map (fn [id]
;;                     (html
;;                       (str id) [:br]))
;;                   ids))
;;       (render-records-with-values ids))))


(defn entity-view [id-string]
  (kabob-page id-string
    (let [entity (read-string id-string)
          ids (ice-denoting edu.ucdenver.ccp.kr.kb/*kb* entity)]
      (prn ids)
      (html [:div "IDs:" [:br]
             (doall
              (map (fn [id] (html (str id) [:br]))
                   ids))]
            [:br] [:br]
            (render-records-with-values ids)))))

;; (defn entity-view [id-string]
;;   (prn id-string)
;; (clojure.core/binding
;;  [edu.ucdenver.ccp.kr.kb/*kb* (edu.ucdenver.ccp.kabob.view.kb/view-kb)]
;;  (hiccup.page/html5
;;   (edu.ucdenver.ccp.kabob.view.utils/header id-string)
;;   (let
;;       [entity (read-string id-string)
;;        foo (prn entity)
;;        ids (ice-denoting (view-kb) entity)
;;        bar (prn ids)]
;;     (html ;[:div
;;            (doall
;;             (map (fn [id] (html (str id) [:br])) ids))
;;            [:br]
;;            [:br]
;;            (render-records-with-values ids))))))

(defroutes entity-routes
  (GET "/" {params :params}    (entity-view (:id params)))
  (GET "/dna" {params :params} (dna-view (:id params))))


;;need a generic id page that will look up the id's type
;;  and then dispatch to the appropriate rendering