(ns com.jiesoul.codewar.prime)

(defn prime-1 [n]
  )

(def primes
  (concat
    [2 3 5 7]
    (lazy-seq
      (let [primes-from
            (fn primes-from [n [f & r]]
              (if (some #(zero? (rem n %))
                        (take-while #(<= (* % %) n) primes))
                (recur (+ n f) r)
                (lazy-seq (cons n (primes-from (+ n f) r)))))
            wheel (cycle [2 4 2 4 6 2 6 4 2 4 6 6 2 6 4 2
                          6 4 6 8 4 2 4 2 4 8 6 4 6 2 4 6
                          2 6 6 4 2 4 6 2 6 4 2 4 2 10 2 10])]
        (primes-from 11 wheel)))))

(def ordinals-and-primes (map vector (iterate inc 1) primes))

(take 5 (drop 1000 ordinals-and-primes))

(defn prime [n]
  (letfn [(step [coll]
            (let [head (first coll)]
              (lazy-cat (cons head (step (filter #(pos? (rem % head)) coll))))))]
    (step (range 2 (inc n)))))

(defn prime1 [n]
  (let [q (Math/sqrt n)]
    (lazy-seq (loop [r []
                     c (range 2 (inc n))]
                (let [v (first c)
                      coll (rest c)]
                  (if (> v q)
                    (concat r c)
                    (recur (conj r v) (filter #(pos? (rem % v)) coll))))))))

(defn step [n]
  (filter #(or (< % (/ n 2)) (= % 2)) (prime1 n)))