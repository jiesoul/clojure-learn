(ns com.jiesoul.codewar.last
  (:refer-clojure :exclude [last]))

(defn last
  "Returns the last element of an ISeq"
  [lst]
  (if (seq (rest lst))
    (last (rest lst))
    (first lst)))