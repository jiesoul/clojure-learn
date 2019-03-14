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
