(ns com.jiesoul.codewar.carboat)

(defn step [n]
  (for [x (range 2 n)
        y (range 2 n)
        :let [z (* x y)]
        :when (< z n)]
    z))

(defn howmuch [m n]
  (for [a (map #(inc %) (step n))
        b (map #(+ 2 %) (step n))
        :when (= a b)]
    [a b]))
