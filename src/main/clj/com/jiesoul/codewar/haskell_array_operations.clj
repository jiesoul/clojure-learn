(ns com.jiesoul.codewar.haskell-array-operations)

(defn head [c]
  (first c))

(defn tail [c]
  (rest c))

(defn init [c]
  (-> c
      reverse
      rest
      reverse))

(defn last_ [c]
  (last c))