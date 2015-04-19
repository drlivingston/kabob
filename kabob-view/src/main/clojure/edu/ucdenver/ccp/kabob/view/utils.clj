(ns edu.ucdenver.ccp.kabob.view.utils
 (use compojure.core
      [hiccup core page element]
      ;;edu.ucdenver.ccp.kr.kb
      edu.ucdenver.ccp.kabob.view.kb))

(defn header [title-str]
  (html5
   [:head
    [:title title-str]
    (include-css "/css/reset.css"
                 "/css/kabob.css")
    (include-js "/js/jquery-1.9.1.min.js")]))

(defmacro kabob-page [title & body]
;;  `(binding [edu.ucdenver.ccp.kr.kb/*kb* (view-kb)]
  `(with-kabob-connection
     ;;(html5 [:head] [:body "foo"])))
     (html5
      (header ~title)
      [:body
       [:h1 ~title]
       ~@body])))


(defn record-link [r]
  (html
   (link-to {:class "record-link"} (str "/kabob/ice/record?id=" r) r)))

(defn value-link [val]
  (html
   (link-to {:class "value-link"} (str "/kabob/ice/value?value=" val) val)))

