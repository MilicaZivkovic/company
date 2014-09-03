(ns company.routes.employees
  (:require [compojure.core :refer :all]
            [company.views.layout :as layout]
            [hiccup.form :refer :all]
            [company.models.db :as db]
            [hiccup.page :as hic-p]))

(defn all-empoloyees
  []
  (let [all-emplys (db/read-employees)]
     [:h1 "All Employees"]
     [:table
      [:tr [:th "Id"] [:th "First Name"] [:th "Last Name"] [:th "Years of service"] [:th "Place"][:th "Sector"][:th "Delete place"]]
      (for [emp all-emplys]
        [:tr [:td (:id emp)] [:td (:emp_name emp)] [:td (:surname emp)] [:td (:yrs_of_svc emp)] [:td (:plc_name emp)] [:td (:sec_name emp)][:td [:input.deletePlace {:type "submit" :value "Delete"}]]])]))

(defn home [& [id name error]]
  (layout/common
   [:p error]
   (all-empoloyees)))

(defroutes employee-routes
  (GET "/employee" [] (home)))







