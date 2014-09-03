(ns company.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [company.routes.home :refer [home-routes]]
            [company.routes.places :refer [place-routes]]
            [company.routes.sectors :refer [sector-routes]]
            [company.routes.employees :refer [employee-routes]]))



(defn init []
  (println "company is starting"))

(defn destroy []
  (println "company is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes employee-routes sector-routes place-routes home-routes app-routes)
      (handler/site)
      (wrap-base-url)))


