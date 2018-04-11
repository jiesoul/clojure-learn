(ns dataclojure.jdbc
  (:require [incanter.core :refer :all]
            [clojure.java.jdbc :refer [with-connection with-query-results]]))

(defn load-table-data [db table-name]
  (let [sql (str "SELECT * FROM " table-name ";")]
    (with-connection
      db
      (with-query-results
        rs [sql]
        (to-dataset (doall rs))))))

(def db {:subprotocol "sqlite",
         :subname "data/small-sample.sqlite"
         :classname "org.sqlite.JDBC"})

(load-table-data db 'people)