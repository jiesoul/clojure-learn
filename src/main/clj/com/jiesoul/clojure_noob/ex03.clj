(ns com.jiesoul.clojure-noob.ex03)

(defn dec-maker [m]
  (fn [n]
    (- n m)))

(defn mapset [f coll]
  (->> coll
    (map f)
    set))
