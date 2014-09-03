(ns company.routes.home
  (:require [compojure.core :refer :all]
            [company.views.layout :as layout]
            [hiccup.form :refer :all]
            [company.models.db :as db]
            [hiccup.page :as hic-p]))


(defn home []
(layout/common
[:h1 "Company"]
[:p "Welcome to admin page"]))

(defroutes home-routes
  (GET "/" [] (home)))





