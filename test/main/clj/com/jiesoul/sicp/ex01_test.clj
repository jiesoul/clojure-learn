(ns com.jiesoul.sicp.ex01-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.sicp.ex01 :refer :all]
            [com.jiesoul.sicp.ch01 :refer [prime?]]))

(deftest f-12-test
  (testing "test"
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

(deftest f-11-iter-test
  (testing "test 1.11"
    (is (= (f-11-iter 0) 0))
    (is (= (f-11-iter 1) 1))
    (is (= (f-11-iter 2) 2))
    (is (= (f-11-iter 3) 8))
    (is (= (f-11-iter 4) 29))
    (is (= (f-11-iter 5) 105))
    ))

(deftest fast-expt-iter-test
  (testing "ex1.16"
    (is (= (fast-expt-iter 2 4) 16))
    (is (= (fast-expt-iter 2 3) 8))
    (is (= (fast-expt-iter 3 1) 3))
    (is (= (fast-expt-iter 3 0) 1))
    ))

(deftest new-mul-iter-test
  (testing "1.17"
    (is (= (new-mul-iter 4 5) 20))
    (is (= (new-mul-iter 0 5) 0))
    (is (= (new-mul-iter 5 0) 0))
    (is (= (new-mul-iter 5 5) 25))
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
    (is (= (sum-cubes-iter 1 10) 3025))
    (is (= (sum-integers-iter 1 10) 55))
    (is (= (* 8 (pi-sum-iter 1 1000)) 3.139592655589782))
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
    (is (= (f-35) 1.6180327868852458))
    ))

(deftest cont-frac-test
  (testing "cont-frac-test"
    (let [v (/ 1 1.6180327868852458)]
      (is (< (Math/abs (- (cont-frac (fn [_] 1.0) (fn [_] 1.0) 4) v)) 0.1))
      (is (< (Math/abs (- (cont-frac (fn [_] 1.0) (fn [_] 1.0) 50) v)) 0.01))
      (is (< (Math/abs (- (cont-frac (fn [_] 1.0) (fn [_] 1.0) 500) v)) 0.001))
      (is (< (Math/abs (- (cont-frac (fn [_] 1.0) (fn [_] 1.0) 5) v)) 0.0001))
      (is (< (Math/abs (- (cont-frac (fn [_] 1.0) (fn [_] 1.0) 5000) v)) 0.0000001))
      )
    ))
