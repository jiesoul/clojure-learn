()

(defn load-table-data [db table-name]
  (let [sql (str "SELECT * FROM " table-name ";")]
    (with-connection db
                     (with-query-results rs [sql]
                                         (to-dataset (doall rs))))))
