(ns codewar.gapinprimes)

(defn primes [n]
  (letfn [(step [coll]
            (if (empty? coll)
              []
              (let [head (first coll)]
                (lazy-cat (cons head (step (filter #(pos? (rem % head)) (rest coll))))))))]
    (step (range 2 n))))

(defn primes [n]
  (letfn [(step [coll]
            (lazy-seq (loop [r    []
                    coll coll]
               (let [fv (first coll)]
                 (if (> fv (Math/sqrt n))
                   (concat r coll)
                   (recur (conj r fv) (filter #(pos? (rem % fv)) (rest coll))))))))]
    (step (range 2 n))))

(defn gap [g m n]
  (let [xs (primes n)]
    (->> xs
         (filter #(<= m %))
         ;(partition 2 1)
         ;(filter #(= g (- (second %) (first %))))
         first)))
