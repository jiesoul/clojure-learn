(ns com.jiesoul.algorithms-jefffe.ch00)

(defn int-to-vec [n]
  (->> n
       (str)
       (seq)
       (map #(Integer/parseInt (str %)))))

(defn fibonacci-multiply [x y]
  (let [X    (int-to-vec x)
        Y    (int-to-vec y)
        m    (count X)
        n    (count Y)
        hold 0]
    (loop [k 0
           r []]
      (if (> k (+ n m -1))
        r
        (let [hold (reduce + (for [i (range 0 m)
                                   j (range 0 n)
                                   :when (= k (+ i j))]
                               (+ hold (* (nth X i) (nth Y j)))))
              v    (mod hold 10)
              hold (quot hold 10)]
          (recur (inc k) (conj r v)))))))

(defn peasant-multi [x y]
  (loop [x    x
         y    y
         prod 0]
    (if (<= x 0)
      prod
      (let [prod (if (odd? x) (+ prod y) prod)]
        (recur (quot x 2) (+ y y) prod)))))
