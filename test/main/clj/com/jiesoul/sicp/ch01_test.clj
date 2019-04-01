(ns com.jiesoul.sicp.ch01-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.sicp.ch01 :refer :all]))

(deftest square-test
  (testing "square"
    (is (= (square 21) 441))
    (is (= (square (+ 2 5)) 49))
    (is (= (square (square 3)) 81))
    ))

(deftest sum-of-square-test
  (testing ""
    (is (= (sum-of-square 3 4) 25))
    ))

(deftest f-test
  (testing "复合sum-of-squares test"
    (is (= (f 5) 136))
    ))

(deftest abs-test
  (testing "abs test"
    (is (= (abs -1) 1))
    (is (= (abs -1) 1))
    (is (= (abs -1) 1))
    ))

(deftest sqrt-test
  (testing "test sqrt"
    (is (= (sqrt 9) 3.0))
    (is (= (sqrt (+ 100 37)) 11.704699910719626))
    (is (= (sqrt (+ (sqrt 2) (sqrt 3))) 1.7737712281868727))
    (is (= (square (sqrt 1000)) 1000.0000000000343))
    ))

(deftest new-sqrt-test
  (testing "test"
    ;(is (= (new-sqrt 9) 3.000000000000002))
    ))

(deftest cube-root-test
  (testing "test"
    (is (= (Math/round (cube-root 27)) 3))
    (is (= (Math/round (cube-root 8)) 2))
    (is (= (Math/round (cube-root 64)) 4))
    ))

(deftest factorial-test
  (testing ""
    (is (= (factorial 6) 720))
    ))

(deftest fib-test
  (testing "test fib"
    (is (= (fib 1) 1))
    (is (= (fib 5) 5))
    (is (= (fib 6) 8))
    (is (= (fib 60) 1548008755920))
    ))

(deftest count-change-test
  (testing "test"
    (is (= (count-change 100) 292))
    ))

(deftest sine-test
  (testing "test"
    (is (= (sine 12.15) -0.39980345741334))
    (is (= (sine 16) -0.29897692921835617))
    (is (= (sine 160) 0.07807229364757007))
    ))

(deftest smallest-divisor-test
  (testing "sss"
    (is (= (smallest-divisor 199) 199))
    (is (= (smallest-divisor 1999) 1999))
    (is (= (smallest-divisor 19999) 7))
    ))

(deftest sum-cubes-test
  (testing "ss"
    (is (= (sum-cubes 1 10) 3025))
    ))

(deftest sum-integers-test
  (testing "sum-integers"
    (is (= (sum-integers 1 10) 55))
    ))

(deftest pi-sum-test
  (testing "ssss"
    (* 8 (pi-sum 1 10000))
    ))

(deftest integral-test
  (testing "integral"
    (is (= (integral cube 0 1 0.01) 0.24998750000000042))
    (is (= (integral cube 0 1 0.001) 0.24999987500000073))
    ))

(deftest xps-integral-test
  (testing "ex 1.29"
    (is (= (xps-integral cube 0 1 100) 0.25))
    (is (= (xps-integral cube 0 1 1000) 0.25))
    ))

(deftest half-interval-method-test
  (testing "half-interval-method-test"
    (is (= (half-interval-method sin 2.0 4.0) 3.14111328125))
    (is (= (half-interval-method #(- (* % % %) (* 2 %) 3) 1.0 2.0) 1.89306640625))
    ))

(deftest fixed-point-test
  (testing "fixed-point-test"
    (is (= (fixed-point cos 1.0) 0.7390822985224024))
    (is (= (fixed-point #(+ (sin %) (cos %)) 1.0) 1.2587315962971173))
    (is (= (fixed-point #(/ (Math/log 1000) (Math/log %)) 10) 4.555532257016376))
    ))

(deftest A-test
  (testing "ex 1.0"
    (is (= (A 1 10) 1024))
    (is (= (A 2 4) 65536))
    (is (= (A 3 3) 65536))
    ))

(deftest f-11-iter-test
  (testing "test ex1.11"
    (is (= (f-11-iter 0) 0))
    (is (= (f-11-iter 1) 1))
    (is (= (f-11-iter 2) 2))
    (is (= (f-11-iter 3) 8))
    (is (= (f-11-iter 4) 29))
    (is (= (f-11-iter 5) 105))
    ))

(deftest f-12-test
  (testing "test ex1.12"
    (is (= (f-12 1) [1]))
    (is (= (f-12 2) [1 1]))
    (is (= (f-12 3) [1 2 1]))
    (is (= (f-12 4) [1 3 3 1]))
    (is (= (f-12 5) [1 4 6 4 1]))
    ))

(deftest max-three-test
  (testing "test 1.3"
    (is (= (max-three 1 2 3) 5))
    (is (= (max-three 2 1 3) 5))
    (is (= (max-three 2 3 1) 5))
    ))



(deftest fast-expt-iter-test
  (testing "ex1.16"
    (is (= (fast-expt-iter 2 4) 16))
    (is (= (fast-expt-iter 2 3) 8))
    (is (= (fast-expt-iter 3 1) 3))
    (is (= (fast-expt-iter 3 0) 1))
    ))

(deftest new-mul-iter-test
  (testing "test ex1.17"
    (is (= (new-mul-iter 4 5) 20))
    (is (= (new-mul-iter 0 5) 0))
    (is (= (new-mul-iter 5 0) 0))
    (is (= (new-mul-iter 5 5) 25))
    (is (= (new-mul-iter 5 6) 30))
    ))

(deftest timed-prime-test-test
  (testing "1.22"
    (timed-prime-test 5)
    ))

(deftest search-for-primes-test
  (testing "search-for-primes"
    (time (search-for-primes 1000))
    (time (search-for-primes 10000))
    (time (search-for-primes 100000))
    (time (search-for-primes 1000000))
    ))

(deftest sum-cubes-iter-test
  (testing "sum-iter"
    (is (= (sum-cubes 1 10) 3025))
    (is (= (sum-integers 1 10) 55))
    (is (= (* 8 (pi-sum 1 1000)) 3.139592655589782))
    ))

(deftest product-factorial-test
  (testing "sss"
    (is (= (product-factorial 3) 6))
    (is (= (product-factorial 4) 24))
    (is (= (product-factorial 5) 120))
    ))

(deftest product-pi-test
  (testing "product-pi"
    (let [pi (/ Math/PI 4)]
      (is (< (- (product-pi 3) pi) 0.1))
      (is (< (- (product-pi 30) pi) 0.1))
      (is (< (- (product-pi 300) pi) 0.01))
      (is (< (- (product-pi 3000) pi) 0.001)))
    ))

(deftest product-iter-factorial-test
  (testing "product-iter-factoril"
    (is (= (product-iter-factorial 3) 6))
    (is (= (product-iter-factorial 4) 24))
    (is (= (product-iter-factorial 5) 120))
    ))

(deftest product-iter-pi-test
  (testing "product-iter-pi"
    (let [pi (/ Math/PI 4)]
      (is (< (- (product-iter-pi 3) pi) 0.1))
      (is (< (- (product-iter-pi 30) pi) 0.1))
      (is (< (- (product-iter-pi 300) pi) 0.01))
      (is (< (- (product-iter-pi 30000) pi) 0.0001))
      (is (< (- (product-iter-pi 3000) pi) 0.001)))
    ))

(deftest accumulate-sum-fac-test
  (testing "accumulate-sum-fac"
    (is (= (accumulate-sum-fac 1 3) 6))
    ))


(deftest accumulate-product-factorial-test
  (testing "accumulate-product-factorial-test"
    (is (= (accumulate-product-factorial 3) 6))
    (is (= (accumulate-product-factorial 4) 24))
    (is (= (accumulate-product-factorial 5) 120))
    ))

(deftest filtered-accumulate-prime-sum-test
  (testing "filtered-accumulate-prime-sum-test"
    (is (= (filtered-accumulate-prime-sum 2 10) 17))
    ))

(deftest filtered-accumulate-product-gcd-test
  (testing "filtered-accumulate-product-gcd-test"
    (is (= (filtered-accumulate-product-gcd 10) 189))
    (is (= (filtered-accumulate-product-gcd 11) 3628800))
    ))

(deftest f-35-test
  (testing "f-35"
    (is (= (ex-35) 1.6180327868852458))
    ))

(deftest cont-frac-test
  (testing "cont-frac-test"
    (let [v (/ 1 1.6180327868852458)]
      (is (< (Math/abs (- (cont-frac (fn [_] 1.0) (fn [_] 1.0) 4) v)) 0.1))
      (is (< (Math/abs (- (cont-frac (fn [_] 1.0) (fn [_] 1.0) 50) v)) 0.01))
      (is (< (Math/abs (- (cont-frac (fn [_] 1.0) (fn [_] 1.0) 500) v)) 0.001))
      (is (> (Math/abs (- (cont-frac (fn [_] 1.0) (fn [_] 1.0) 5) v)) 0.0001))
      (is (> (Math/abs (- (cont-frac (fn [_] 1.0) (fn [_] 1.0) 5000) v)) 0.0000001))
      )
    ))
