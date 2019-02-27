(ns com.jiesoul.codewar.moves-in-squared-strings-3
  (:require [clojure.string :as str]))

(defn rot-90-clock-1 [strng]
  (->> strng
       (clojure.string/split-lines)
       (apply map vector)
       (map reverse)
       (map #(apply str %))
       (clojure.string/join \newline)))

(defn diag-1-sym-1 [strng]
  (->> strng
       (clojure.string/split-lines)
       (apply map vector)
       (map #(apply str %))
       (clojure.string/join \newline)))

(defn selfie-and-diag1-1 [strng]
  (let [orgin (clojure.string/split-lines strng)]
    (->> orgin
         (apply map vector)
         (map #(str %1 "|" (apply str %2)) orgin)
         (clojure.string/join \newline))))

(defn oper-1 [fct s]
  (fct s))

(defn transpose [s]
  (apply map vector s))

(defn rot-90-clock [s]
  (->> s
       (transpose)
       (map reverse)
       (map #(apply str %))))

(defn diag-1-sym [s]
  (->> s
       (transpose)
       (map #(apply str %))))

(defn selfie-and-diag1 [s]
  (map #(str %1 \| %2) s (diag-1-sym s)))

(defn oper [fct s]
  (->> s
       (str/split-lines)
       (fct)
       (str/join \newline)))

(def s "abcd\nefgh\nijkl\nmnop")

(diag-1-sym s)
(rot-90-clock s)
(selfie-and-diag1 s)
(oper diag-1-sym s)