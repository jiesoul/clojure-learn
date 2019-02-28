(ns com.jiesoul.codewar.compsqstrings
  (:require [clojure.string :as str]))

(defn compose [s1 s2]
  (let [c1 (str/split-lines s1)
        c2 (reverse (str/split-lines s2))
        l (inc (count c1))]
    (loop [n 1
           r []
           c1 c1
           c2 c2]
      (if (empty? c1)
        (str/join \newline r)
        (let [v1 (apply str (take n (first c1)))
              v2 (apply str (drop-last (dec n) (first c2)))
              v (str v1 v2)]
          (recur (inc n) (conj r v) (rest c1) (rest c2)))))))

(defn compose-1 [s1 s2]
  (let [c2 (vec (reverse (str/split-lines s2)))
        n (count c2)
        c1 (map-indexed (fn [i v]
                          (str (subs v 0 (inc i)) (subs (c2 i) 0 (- n i))))
                        (str/split-lines s1))]
    (str/join \newline c1)))

(def s1 "abcd\nefgh\nijkl\nmnop")
(def s2 "qrst\nuvwx\nyz12\n3456")
(compose s1 s2)
(compose-1 s1 s2)

