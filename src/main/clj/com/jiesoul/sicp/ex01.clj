(ns com.jiesoul.sicp.ex01
  (:require [com.jiesoul.sicp.ch01 :refer [prime? cube pi-term pi-next gcd fixed-point]]))







;; 1.32
(defn accumulate [combiner null-value term a next b]
  (if (> a b)
    null-value
    (combiner (term a)
              (accumulate combiner null-value term (next a) next b))))

(defn accumulate-sum-fac [a b]
  (accumulate + 0 identity a inc b))

(defn accumulate-product-factorial [n]
  (accumulate * 1 identity 1 inc n))

(defn accumulate-iter [combiner null-value term a next b]
  (let [step (fn [a result]
               (if (> a b)
                 result
                 (recur (next a) (combiner result (term a)))))]
    (step a null-value)))

;; 1.33
(defn filtered-accumulate [filtered combiner null-value term a next b]
  (let [step (fn [a result]
               (if (> a b)
                 result
                 (recur (next a) (if (filtered a)
                                   (combiner result (term a))
                                   result))))]
    (step a null-value)))

(defn filtered-accumulate-prime-sum [a b]
  (filtered-accumulate prime? + 0 identity a inc b))

(defn filtered-accumulate-product-gcd [n]
  (filtered-accumulate (fn [a]
                         (= (gcd a n) 1))
                       *
                       1
                       identity
                       1
                       inc
                       n))

;; 1.35
(defn f-35 []
  (fixed-point #(+ 1 (/ 1.0 %)) 1.0))

;; 1.37
(defn cont-frac [n d k]
  (let [step (fn step [i]
               (if (> i k)
                 0
                 (/ (n i) (+ (d i) (step (inc i))))))]
    (step 1)))

(defn cont-frac-iter [n d k]
  (let [step (fn [i])]))
