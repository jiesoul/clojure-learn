(ns com.jiesoul.codewar.backwards-prime
  (:require [com.jiesoul.codewar.primes :refer :all]))

(defn backwards [n]
  (loop [q (quot n 10)
         r (rem n 10)]
    (if (zero? q)
      r
      (recur (quot q 10) (+ (rem q 10) (* r 10))))))

(defn backwards-prime [start stop]
  (for [coll (filter #(>= % start) (primes-n stop)) :when (and (primes? (backwards coll)) (not= coll (backwards coll)))]
    coll))