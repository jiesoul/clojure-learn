(ns com.jiesoul.sicp.ch01-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.sicp.ch01 :refer :all]))

(deftest square-test
  (testing "square"
    (is (= (square 21) 441))
    (is (= (square (+ 2 5)) 49))
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
    (is (= (abs-1 -1) 1))
    (is (= (abs-2 -1) 1))
    ))

(deftest sqrt-test
  (testing "test sqrt"
    (is (= (sqrt 9) 3.000000000000002))
    (is (= (sqrt (+ 100 37)) 11.704699910719627))
    (is (= (sqrt (+ (sqrt 2) (sqrt 3))) 1.7737712281890188))
    (is (= (square (sqrt 1000)) 1000.0000000000928))
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
    ))

(deftest fib-iter-test
  (testing "test fib-iter"
    (is (= (fib-iter 1) 1))
    (is (= (fib-iter 5) 5))
    (is (= (fib-iter 6) 8))
    (is (= (fib-iter 60) 1548008755920))
    ))

(deftest count-change-test
  (testing "test"
    (is (= (count-change 100) 292))
    ))

(deftest sine-test
  (testing "test"
    (is (= (sine 12.15) -0.39980345741334))
    (is (= (sine 16) -0.29897692921835617))
    (is (= (sine 160) -0.07807229364757007))
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
    (is (= (integral cube 0 1 0.001) 0.249999875000001))
    ))
