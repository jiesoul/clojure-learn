(ns com.jiesoul.codewar.Slope)

(defn slope [v]
  (let [a (first v)
        b (second v)
        c (nth v 2)
        d (last v)]
    (cond
      (= a c) "undefined"
      (= b d) "0"
      :else
      (str (/ (- d b) (- c a))))))
