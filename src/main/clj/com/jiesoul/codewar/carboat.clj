(ns com.jiesoul.codewar.carboat)

(defn step [n]
  (for [x (range 2 n)
        y (range 2 n)
        :let [z (* x y)]
        :when (< z n)]
    z))

(defn howmuch [m n]
  (let [max (max m n)
        min (min m n)
        c 7
        b 9]
    (for [x (range max)
          :let [f (+ 2 (* x c))
                y (/ (- f 1) b)]
          :when (and
                  (= y (int y))
                  (<= min f max))]
      [(str "M: " f) (str "B: " x) (str "C: " y)])))

(howmuch 1 100)
(howmuch 2950 2950)
