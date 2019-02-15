(ns com.jiesoul.codewar.emirps)

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

(defn find-emirp
  [n]
  (letfn [(primes []
            (letfn [(step [coll]
                      (let [head (first coll)]
                        (lazy-cat [] [head] (step (filter #(pos? (mod % head)) coll)))))]
              (step (range 2 Long/MAX_VALUE))))
          (primes? [n]
            (let [coll (range 2 (inc (Math/sqrt n)))]
              (not-any? #(zero? (rem n %)) coll)))
          (primes2? [n]
            (= n (last (take-while #(<= % n) (primes)))))
          (reverse-num [d]
            (Integer/parseInt (apply str (reverse (seq (str d))))))]
    (let [xs (take-while #(<= % n) (primes))
          xs (filter #(not= % (reverse-num %)) xs)
          xs (filter #(primes? (reverse-num %)) xs)]
      [(count xs), (apply max xs), (reduce + xs)])))