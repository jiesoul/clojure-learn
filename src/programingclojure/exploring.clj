(ns programingclojure.exploring
  (:require [clojure.string :as str]))

(defn gretting
  " 返回名字"
  [username]
  (str "Hello, " username))

(defn date [person-1 person-2 & chapersons]
  (println person-1 " and " person-2 "went out with" (count chapersons) "chapersons."))

(date "jie" "john" "frod")

(defn indexable-word? [word]
  (> (count word) 2))

(filter indexable-word? (str/split "A fine day it is" #"\W+"))

(filter (fn [w] (> (count w) 2)) (str/split "A fine day it is" #"\W+"))

(filter #(> (count %) 2) (str/split "A fine day it is" #"\W+"))

(def foo 10)

(var foo)

#'foo

(defn square-corners [bottom left size]
  (let [top (+ bottom size)
        right (+ left size)]
    [[bottom left] [top left] [top right] [bottom right]]))

(defn greet-author-1 [author]
  (println "Hello, " (:first-name author)))

(greet-author-1 {:last-name "Vinge" :first-name "Verne"})

(defn greet-author-2 [{fname :first-name}]
  (println "Hello, " fname))

(greet-author-2 {:first-name "jie" :last-name "soul"})

(defn ellipsize [words]
  (let [[w1 w2 w3] (str/split words #"\s+")]
    (str/join "" [w1 w2 w3 "..."])))

(ellipsize "The quick brown fox over the lazy dog.")

(defn is-small? [number]
  (if (< number 100)
    "yes"
    (do
      (println "Saw a big number" number)
    "no")))

(is-small? 50)
(is-small? 5000)

(loop [result []
       x 5]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))

(defn indexed [coll]
  (map-indexed vector coll))

(indexed "abcde")

(defn index-filter [pred coll]
  (when pred
    (for [[idx elt] (indexed coll) :when (pred elt)] idx)))

(index-filter #{\a \b} "abcdbbb")

(index-filter #{\a \b} "xyz")

(defn index-of-any [pred coll]
  (first (index-filter pred coll)))

(index-of-any #{\z \a} "zzabyycdxx")

(index-of-any #{\b \y} "zzabyycdxx")

(meta #'str)

(defn ^String shout [^String s]
  (.toUpperCase s))

(meta #'shout)



