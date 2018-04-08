(ns clj-data-anal.network-six.graph
  (:require [clojure.set :as set]
            [clojure.data.json :as json]
            [clojure.java.io :as io]
            [clojure.set :as set]
            [network-six.util :as u]))

(defrecord Graph
  [neighbors data])

(def empty-graph (Graph. {} {}))

(defn update-conj [s x]
  (conj (if (nil? s) #{} s) x))

(defn add
  ([g x y] (add g x y false))
  ([g x y bidrectional?]
    ((if bidrectional? #(add % x y false) identity)
      (update-in g [:neighbors x] #(update-conj % y)))))

(defn delete
  ([g x y] (delete g x y false))
  ([g x y bidirectinal?]
    ((if bidirectinal? #(delete % x y false) identity)
      (update-in g [:neighbors x] #(disj % y)))))

(defn merge-graphs [a b]
  (Graph. (merge-with set/union (:neighbors a) (:neighbors b))
          (merge (:data a) (:data b))))

(defn get-value
  ([g x] ((:data g) x))
  ([g x k] ((get-value g x) k)))

(defn set-value
  ([g x v] (assoc-in g [:data x] v))
  ([g x k v] (set-value g x (assoc (get-value g x) k v))))

(defn update-value
  ([g x f] (set-value g x (f (get-value g x))))
  ([g x k f] (set-value g x k (f (get-value g x k)))))