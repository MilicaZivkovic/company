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
      [:tr [:th "Id"] [:th "First Name"] [:th "Last Name"] [:th "Years of service"] [:th "Place"][:th "Sector"][:th "Delete"]]
      (for [emp all-emplys]
        [:tr [:td (:id emp)] [:td (:emp_name emp)] [:td (:surname emp)] [:td (:yrs_of_svc emp)] [:td (:plc_name emp)][:td (:sec_name emp)]
         [:td [:input.deleteEmployee {:type "submit" :value "Delete"}]]])]))

(defn add-employee
  []
  (let [all-places (db/read-places)
        all-sectors (db/read-sectors)]
    [:form
    [:p "Id value: " [:input {:type "text" :name "id" :id "employeeId"}]]
    [:p "First name: " [:input {:type "text" :name "name" :id "employeeName"}]]
    [:p "Last name: " [:input {:type "text" :name "surname" :id "employeeSurname"}]]
    [:p "Years of service: " [:input {:type "text" :name "yrs_of_svc" :id "employeeYearsOfService"}]]
    [:p "Places:" [:select {:id "places"} (for [plc all-places]
                             [:option {:id (:id plc) :value (:id plc) } (:name plc)])] ]
    [:p "Sectors:" [:select {:id "sectors"}(for [sec all-sectors]
                             [:option {:id (:id sec) :value (:id sec) } (:name sec)])] ]
    [:p [:input.insertEmployee {:type "submit" :value "Add employee"}]]]))


(defn home [& [id name error]]
  (layout/common
   [:p error]
   (all-empoloyees)
   [:hr]
   (add-employee)))

(defn save-employee [id name surname yrs_of_svc place_id sector_id]
  (let [id (Integer/parseInt id)
        yrs_of_svc (Integer/parseInt yrs_of_svc)
        place_id (Integer/parseInt place_id)
        sector_id (Integer/parseInt sector_id)]
    (db/insert-employee id name surname yrs_of_svc sector_id place_id)
    (home)))

(defn remove-employee [id]
 (do (let [id (Integer/parseInt id)]
    (db/delete-employee id))))

(defroutes employee-routes
  (GET "/employee" [] (home))
  (POST "/save-employee" [id name surname yrs_of_svc place_id sector_id] (save-employee id name surname yrs_of_svc place_id sector_id))
  (DELETE "/remove-employee" [id] (remove-employee id)))











