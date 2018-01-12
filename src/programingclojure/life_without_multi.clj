(ns programingclojure.life-without-multi)

(defn my-print [ob]
  (.write *out* ob))

(defn my-println [ob]
  (my-print ob)
  (.write *out* "\n"))
