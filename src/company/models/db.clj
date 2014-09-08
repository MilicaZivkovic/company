(ns company.models.db
(:require [clojure.java.jdbc :as sql])
(:import java.sql.DriverManager)
(:import java.util.Date))

(def db
    {:classname "org.postgresql.Driver"
     :subprotocol "postgresql"
     :subname "//localhost:5432/company"
     :username "Milica"
     :password "milica"})

;;place
(defn insert-place [id name]
  (sql/insert! db :place
                {:id id
                 :name name}))

(defn update-place [id name]
  (sql/update! db :place
               {:name name}
               ["id = ?" id]))

(defn delete-place [id]
 (sql/delete! db :place ["id = ?" id]))

(defn read-places []
 (sql/query db
 ["select * from place order by id"]))

;;sector
(defn insert-sector [id name]
  (sql/insert! db :sector
                {:id id
                 :name name}))

(defn update-sector [id name]
  (sql/update! db :sector
               {:name name}
               ["id = ?" id]))

(defn delete-sector [id]
 (sql/delete! db :sector ["id = ?" id]))

(defn read-sectors []
 (sql/query db
 ["select * from sector order by id"]))

;;empolyee
(defn insert-employee [id name surname yrs_of_svc sector_id place_id]
  (sql/insert! db  :employee
               {:id id
                :name name
                :surname surname
                :yrs_of_svc yrs_of_svc
                :sector_id sector_id
                :place_id place_id}))

(defn update-employee [id name surname yrs_of_svc sector_id place_id]
  (sql/update! db :employee
                {:name name
                 :surname surname
                 :yrs_of_svc yrs_of_svc
                 :sector_id sector_id
                 :place_id place_id} ["id = ?" id]))

(defn delete-employee [id]
 (sql/delete! db :employee ["id = ?" id]))

(defn read-employees []
  (sql/query db
   ["select e.id, e.name as emp_name, e.surname, e.yrs_of_svc, p.name as plc_name, s.name as sec_name from (employee e inner join place p on e.place_id=p.id) inner join sector s on e.sector_id = s.id"]))

;;salary
(defn insert-salary [emp_id amount]
  (sql/insert! db :salary
                {:emp_id emp_id
                 :amount amount
                 :date  (java.sql.Timestamp.  (.getTime (java.util.Date.) ))}))

(defn update-salary [emp_id amount]
  (sql/update! db :salary
                {:amount amount} ["emp_id = ?" emp_id]))


















