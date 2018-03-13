(ns programingclojure.concurrency)

(def counter (ref 0))

(defn next-counter []
  (dosync (alter counter inc)))

