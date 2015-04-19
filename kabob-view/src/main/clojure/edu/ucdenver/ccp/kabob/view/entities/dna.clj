(ns edu.ucdenver.ccp.kabob.view.entities.dna
 (use compojure.core
      [hiccup core page]
      edu.ucdenver.ccp.kabob.view.utils
      edu.ucdenver.ccp.kabob.view.record))

;; (defn fv-table [fvs]
;;   (html
;;    [:table {:class "data-table"}
;;     [:thead
;;      [:tr
;;       [:th "field"]
;;       [:th "value"]]]
;;     [:tbody
;;      ;(html5
;;       (map (fn [[f v]]
;;              (html
;;               [:tr
;;                [:td f]
;;                [:td v]]))
;;            fvs);)
;;     ]]))




(defn dna-view [id]
  (html5
   (header id)
   [:body
    [:h1 id]
    [:br]
    [:div
     "hello"
     "merged ids"
     [:br]
     [:br]
     "records"
     (fv-table '[["foo" 1] ["bar" "baz"]]) ]
    ]))



