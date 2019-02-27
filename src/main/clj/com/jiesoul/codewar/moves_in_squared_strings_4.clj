(ns com.jiesoul.codewar.moves-in-squared-strings-4
  (:require [clojure.string :as str]))

(defn transpose [s]
  (apply map vector s))

(defn rot-90-counter [s]
  (->> s
       (transpose)
       (reverse)
       (map (comp #(apply str %)))))

(defn diag-2-sym [s]
  (->> s
       (transpose)
       (reverse)
       (map (comp #(apply str %) reverse))))

(defn selfie-diag2-counterclock [s]
  (map #(str %1 \| %2 \| %3) s (diag-2-sym s) (rot-90-counter s))
  )


(defn oper [fct s]
  (->> s
       (str/split-lines)
       (fct)
       (str/join \newline)
       ))

(def s "abcd\nefgh\nijkl\nmnop")

(oper diag-2-sym s)
(oper rot-90-counter s)
(oper selfie-diag2-counterclock s)