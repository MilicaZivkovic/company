(ns company.routes.sectors
  (:require [compojure.core :refer :all]
            [company.views.layout :as layout]
            [hiccup.form :refer :all]
            [company.models.db :as db]
            [hiccup.page :as hic-p]))

(defn all-sectors
  []
  (let [all-sec (db/read-sectors)]
     [:h1 "All Sectors"]
     [:table
      [:tr [:th "Id"] [:th "Name"] [:th "Delete sector"]]
      (for [sec all-sec]
        [:tr [:td (:id sec)] [:td (:name sec)] [:td [:input.deleteSector {:type "submit" :value "Delete"}]]])]))


(defn add-sector []
  [:h1 "Add a sector"]
  [:form {:action "/sector" :method "POST"}
   [:p "Id value: " [:input {:type "text" :name "id"}]]
   [:p "Name value: " [:input {:type "text" :name "name"}]]
   [:p [:input {:type "submit" :value "Add sector"}]]])


(defn home [& [id name error]]
  (layout/common
   [:p error]
   (all-sectors)
   [:hr]
   (add-sector)))

(defn save-sector [id name]
(cond
(empty? id)
(home id name "Mandatory field.")
(empty? name)
(home id name "Mandatory field.")
:else
(do
  (let [id (Integer/parseInt id)]
    (db/insert-sector id name)
    (home)))))

(defn remove-sector [id]
 (do (let [id (Integer/parseInt id)]
    (db/delete-sector id)
    )))

(defroutes sector-routes
  (GET "/sector" [id name error] (home id name error))
  (POST "/sector" [id name] (save-sector id name))
  (DELETE "/remove-sector" [id] (remove-sector id)))





