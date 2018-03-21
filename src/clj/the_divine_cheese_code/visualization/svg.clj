(ns the-divine-cheese-code.visualization.svg)

(defn latlng->point
  [latlng]
  (str (:lng latlng) "," (:lat latlng)))

(defn points
  [locations]
  (clojure.string/join " " (map latlng->point locations)))

(str "To understand what recursion is," " you must first understand recursion.")
