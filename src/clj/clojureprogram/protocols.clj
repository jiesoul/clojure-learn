(ns clojureprogram.protocols)

(defprotocol Matrix
  (lookup [matrix i j])
  (update [matrix i j v])
  (rows [matrix])
  (cols [matrix])
  (dims [matrix]))

(extend-protocol Matrix
  clojure.lang.IPersistentVector
  (lookup [vv i j]
    (get-in vv [i j]))
  (update [vv i j v]
    (assoc-in vv [i j] v))
  (rows [vv]
    (seq vv))
  (cols [vv]
    (apply map vector vv))
  (dims [vv]
    [(count vv) (count (first vv))]))
