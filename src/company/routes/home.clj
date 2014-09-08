(ns company.routes.home
  (:require [compojure.core :refer :all]
            [company.views.layout :as layout]
            [hiccup.form :refer :all]
            [company.models.db :as db]
            [hiccup.page :as hic-p]))


(defn home []
(layout/common
 [:h1 "Welcome to admin page"]
 [:p "Please choose working area:"]
 [:input.pageBtn {:type "a" :value "Places" :onclick "window.location.href = '/place'"}][:br]
 [:input.pageBtn {:type "a" :value "Sectors" :onclick "window.location.href = '/sector'"}] [:br]
 [:input.pageBtn {:type "a" :value "Empolyees" :onclick "window.location.href = '/employee'"}]))

(defroutes home-routes
  (GET "/" [] (home)))





