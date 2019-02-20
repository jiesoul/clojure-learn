(ns com.jiesoul.dataclojure.xml
  (:require [incanter.core :refer :all]
            [clojure.xml :refer :all]
            [clojure.zip :refer [xml-zip children down right]]))

(defn load-xml-data [xml-file first-data next-data]
  (let [data-map (fn [node] [(:tag node) (first (:content node))])]
    (->>
      (parse xml-file)
      xml-zip
      first-data
      (iterate next-data)
      (take-while #(not (nil? %)))
      (map children)
      (map #(mapcat data-map %))
      (map #(apply array-map %))
      to-dataset)))

(load-xml-data "data/small-sample.xml" down right)

(map children
     (take-while #(not (nil? %))
                          (iterate right
                                   (down
                                     (xml-zip
                                       (parse "data/small-sample.xml"))))))

(mapcat (fn [node] [(:tag node) (first (:content node))]) '({:tag :given-name, :attrs nil, :content ["Gomez"]}
                                                       {:tag :surname, :attrs nil, :content ["Addams"]}
                                                       {:tag :relation, :attrs nil, :content ["father"]}))