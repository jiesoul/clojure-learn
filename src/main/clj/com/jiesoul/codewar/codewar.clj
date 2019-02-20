(ns com.jiesoul.codewar.codewar
  (:require [clojure.string :as str]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn round [s n]
  (.setScale (bigdec n) s java.math.RoundingMode/HALF_EVEN))













