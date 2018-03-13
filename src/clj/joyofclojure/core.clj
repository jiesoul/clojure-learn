(ns joyofclojure.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn print-seq [s]
  (when (seq s)
    (prn (first s))
    (recur (rest s))))


