(ns com.jiesoul.joyofclojure.ch11
  (:require [joyofclojure.mutation :refer :all]
            [clojure.xml :as xml]
            [clojure.zip :as zip])
  (:import [java.util.regex Pattern]))

(time (let [x (future (do (Thread/sleep 5000) (+ 41 1)))]
        [@x @x]))

(defn feed->zipper [uri-str]
  (->> (xml/parse uri-str)
       zip/xml-zip))

(defn normalize [feed]
  (if (= :feed (:tag (first feed)))
    feed
    (zip/down feed)))

(defn feed-children [uri-str]
  (->> uri-str
       feed->zipper
       normalize
       zip/children
       (filter (comp #(:item :entry) :tag))))

(defn title [entry]
  (some->> entry
           :content
           (some #(when (= :title (:tag %)) %))
           :content
           first))

(defn count-text-task [extractor txt feed]
  (let [items (feed-children feed)
        re (Pattern/compile (str "(?i)" txt))]
    (->> items
         (map extractor)
         (mapcat #(re-seq re %))
         (count))))

(count-text-task
  title
  "Erlang"
  "http://feeds.feedburner.com/ElixirLang")

(count-text-task
  title
  "Elixir"
  "http://feeds.feedburner.com/ElixirLang")