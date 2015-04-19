(ns edu.ucdenver.ccp.kabob.view.server
  (:use compojure.core
        ring.middleware.reload
        ring.middleware.stacktrace
        [hiccup.middleware :only (wrap-base-url)]
        edu.ucdenver.ccp.kabob.view.ice
        edu.ucdenver.ccp.kabob.view.entities)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]
            [ring.adapter.jetty :as jetty])
  (:import org.eclipse.jetty.server.Server))

(defonce server (atom nil))

(defroutes kabob-routes
  (context "/ice"    [] ice-routes)
  (context "/entity" [] entity-routes))

(defroutes intranet-routes
  (context "/kabob" [] kabob-routes)
  (route/resources "/")
  (route/not-found "Page not found"))

;;this should only be in dev due to reload
(def intranet
  (-> (handler/site intranet-routes)
      (wrap-stacktrace) ;logging stack trace to *err* and to web page
      (wrap-reload)     ;this makes it reload namespaces before issuing calls
      (wrap-base-url)))

(defn start-server []
  (reset! server 
          (jetty/run-jetty #'intranet
                           {:host "127.0.0.1" :port 8080 :join? false})))

(defn stop-server []
  (when (not (nil? @server))
    (.stop @server)
    (reset! server nil)))

(defn restart-server[]
  (stop-server)
  (start-server))
