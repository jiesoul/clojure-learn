(ns com.jiesoul.codewar.primes)

(defn filter-rem [n coll]
  (filter #(not= 0 (rem % n)) coll))

(defn primes? [n]
  (let [nums (range 2 (inc (int (Math/sqrt n))))]
    (not-any? zero? (map #(rem n %) nums))))

(defn first-primes [coll]
  (loop [l coll]
    (if (primes? (first l))
      (first l)
      (recur (rest l)))))

(defn primes-gen [n]
  (lazy-seq (loop [coll    (range 2 n)
                   endcoll []]
              (if (> (first coll) (int (Math/sqrt (last coll))))
                (concat coll coll)
                (recur (filter-rem (first coll) coll) (conj endcoll (first coll)))))))

(defn primes-n [n]
  (loop [result []
         coll (range 2 n)]
    (let [head (first coll)]
      (if (> head (int (Math/sqrt (last coll))))
        (concat result coll)
        (recur (conj result head) (filter #(pos? (rem % head)) coll))))))

(def m-primes-n
  (memoize primes-n))

(defn prime [g m n]
  (let [l (filter #(>= % m) (primes-n n))]
    (loop [coll (rest l)
           fact (first l)]
      (cond
        (empty? coll) nil
        (not (nil? (some #{(+ fact g)} coll))) [fact (+ fact g)]
        :else
        (recur (rest coll) (first coll))))))

(def step
  (memoize prime))

(defn primes-list [m n]
  (for [coll (range m n) :when primes?]
    coll))

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
