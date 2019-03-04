(ns com.jiesoul.codewar.lowercase-count)

(defn lowercase_count [strng]
  (->> strng
       (re-seq #"[a-z]+")
       (map count)
       (apply +)))
