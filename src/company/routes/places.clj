(ns company.routes.places
  (:require [compojure.core :refer :all]
            [company.views.layout :as layout]
            [hiccup.form :refer :all]
            [company.models.db :as db]
            [hiccup.page :as hic-p]))

(defn all-places
  []
  (let [all-plc (db/read-places)]
     [:h1 "All Places"]
     [:table
      [:tr [:th "Id"] [:th "Name"] [:th "Delete place"]]
      (for [plc all-plc]
        [:tr
         [:td (:id plc)]
         [:td.editable (:name plc)]
         [:td [:input.deletePlace {:type "submit" :value "Delete"}]]
         [:td [:input.updatePlace {:type "submit" :value "Update"}]]])]))


(defn add-place []
  [:h1 "Add a place"]
  [:form {:action "/place" :method "POST"}
   [:p "Id value: " [:input {:type "text" :name "id"}]]
   [:p "Name value: " [:input {:type "text" :name "name"}]]
   [:p [:input {:type "submit" :value "Add place"}]]])


(defn home [& [id name error]]
  (layout/common
   [:p error]
   (all-places)
   [:hr]
   (add-place)))

(defn save-place [id name]
(cond
(empty? id)
(home id name "Mandatory field.")
(empty? name)
(home id name "Mandatory field.")
:else
(do
  (let [id (Integer/parseInt id)]
    (db/insert-place id name)
    (home)))))

(defn remove-place [id]
 (do (let [id (Integer/parseInt id)]
    (db/delete-place id)
    )))

(defn update-place [id name]
  (do (let [id (Integer/parseInt id)]
    (db/update-place id name))))

(defroutes place-routes
  (GET "/place" [id name error] (home id name error))
  (POST "/place" [id name] (save-place id name))
  (DELETE "/remove-place" [id] (remove-place id))
  (POST "/update-place" [id name] (update-place id name)))





