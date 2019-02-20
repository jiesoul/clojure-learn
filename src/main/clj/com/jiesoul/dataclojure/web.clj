(ns com.jiesoul.dataclojure.web
  (:require [incanter.core :refer :all]
            [clojure.string :as str]
            [net.cgrand.enlive-html :as html])
  (:import [java.net URL]))

(defn to-keyword [input]
  (-> input
      str/lower-case
      (str/replace \space \-)
      keyword))

(defn load-data
  [url]
  (let [html (html/html-resource (URL. url))
        table (html/select html [:table#data])
        headers (->>
                  (html/select table [:tr :th])
                  (map html/text)
                  (map to-keyword)
                  vec)
        rows (->>
               (html/select table [:tr])
               (map #(html/select % [:td]))
               (map #(map html/text %))
               (filter seq))]
    (dataset headers rows)))

(def url (str "http://www.ericrochester.com/clj-data-analysis/data/small-sample-table.html"))
;(load-data url)
(def table (html/select (html/html-resource (URL. url)) [:table#data]))

(mapv to-keyword (map html/text (html/select table [:tr :th])))
(map #(html/text %) (map #(html/select % [:td]) (html/select table [:tr])))

(defn get-family
  ([article]
    (str/join
      (map html/text (html/select article [:header :h2])))))

(defn get-person
  ([li]
    (let [[{pnames :content} rel] (:content li)]
      {:name (apply str pnames)
       :relationship (str/trim rel)})))

(defn get-rows
  ([article]
    (let [family (get-family article)]
      (map #(assoc % :family family)
           (map get-person (html/select article [:ul :li]))))))

(defn load-data
  [html-url]
  (let [html (html/html-resource (URL. html-url))
        articles (html/select html [:article])]
    (to-dataset (mapcat get-rows articles))))

(def url "http://www.ericrochester.com/clj-data-analysis/data/small-sample-list.html")
;(load-data url)


