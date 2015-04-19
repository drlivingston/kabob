(ns edu.ucdenver.ccp.kabob.view.kb
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kabob.kb))

  ;; (:use compojure.core
  ;;       ring.middleware.reload
  ;;       [hiccup.middleware :only (wrap-base-url)]
  ;;       edu.ucdenver.ccp.kabob.view.entities)
  ;; (:require [compojure.route :as route]
  ;;           [compojure.handler :as handler]
  ;;           [compojure.response :as response]
  ;;           [ring.adapter.jetty :as jetty])
  ;; (:import org.eclipse.jetty.server.Server))

(defonce active-kb (atom nil))

(defonce kb-args (atom nil))

;; (binding [*default-server* (:server-url args)
;;             *repository-name* (:repo-name args)
;;             *username* (:username args)
;;             *password* (:password args)]

(defn set-kb-args [args]
  (reset! kb-args args))

(defmacro with-kabob-connection [& body]
  `(let [new-kb# (open-kb @kb-args)]
     ;;(prn new-kb#)
     (prn "kb open")
     (try
       (binding [edu.ucdenver.ccp.kr.kb/*kb* new-kb#]
         ~@body)
       (finally (close new-kb#)
                (prn "kb closed")))))


;; (defn connect-kb [args]
;;   (reset! kb-args args)
;;   (reset! active-kb (open-kb args)))

;; (defn disconnect-kb []
;;   (when (not (nil? @active-kb))
;;     (close @active-kb)
;;     (reset! active-kb nil)))

;; (defn restart-kb-connection[]
;;   (disconnect-kb)
;;   (connect-kb @kb-args))


;; (defn view-kb []
;;   @active-kb)