(ns com.jiesoul.codewar.reverse-or-rotate
  (:require [clojure.string :as str]))

(defn revrot [strng sz]
  (if (or (<= sz 0)
          (empty? strng)
          (> sz (count strng)))
    ""
    (let [coll (partition sz strng)
          od? (fn [c]
                (->> c
                     (map #(Integer/parseInt (str %)))
                     (filter odd?)
                     (count)
                     (odd?)))
          to (fn [c]
               (if (od? c)
                 (let [v (first c)]
                   (conj (vec (rest c)) v))
                 (reverse c)))]
      (->> coll
           (map to)
           (map #(apply str %))
           (apply str)))))

