(ns com.jiesoul.codewar.scalesqstrings
  (:require [clojure.string :as str]))

(defn scale [strng k n]
  (if (= strng "")
    ""
    (let [coll (str/split strng #"\n")]
      (->> coll
        (map seq)
        (map (fn [c] (map #(repeat k (str %)) c)))
        (map (fn [c] (map #(apply str %) c)))
        (map #(apply str %))
        (map #(repeat n %))
        (map #(str/join "\n" %))
        (str/join "\n")))))

(defn scale1 [strng k n]
  (->> (str/split-lines strng)
    (map (comp str/join (partial mapcat (partial repeat k))))
    (mapcat (partial repeat n))
    (str/join \newline)))

(scale "abcd\nefgh\nijkl\nmnop" 2 3)
(scale1 "abcd\nefgh\nijkl\nmnop" 2 3)
(scale "Kj\nSH", 1, 2)
