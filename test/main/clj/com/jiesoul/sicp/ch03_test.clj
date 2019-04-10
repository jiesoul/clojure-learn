(ns com.jiesoul.sicp.ch03-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.sicp.ch03 :refer :all]
            [com.jiesoul.sicp.ch01 :refer [sqrt]]))

(deftest make-account-test
  (testing "test make-account"
    (let [acc (make-account 100)]
      (is (= (acc 'withdraw 50) 50))
      (is (= (acc 'withdraw 60) "Not enough money"))
      (is (= (acc 'deposit 40) 90))
      (is (= (acc 'withdraw 60) 30))
      )))

(deftest make-accumulator-test
  (testing "test ex3.1"
    (let [A (make-accumulator 5)]
      (is (= (A 10) 15))
      (is (= (A 10) 25))
      )))

(deftest make-monitored-test
  (testing "ex3.2 test"
    (let [s (make-monitored sqrt)]
      (is (= (s 100) 10.0))
      (is (> (s 'how-many-calls?) 0))
      (is (zero? (s 'reset-count))))
    ))

(deftest make-account-secret-test
  (testing "test ex 3.3"
    (let [acc (make-account-secret 100 'secret-password)]
      (is (= (acc 'secret-password 'withdraw 40) 60))
      (is (= (acc 'som-other-password 'deposit 50) "Incorrect password"))
      )))

(deftest make-account-secret-test
  (testing "test ex 3.4"
    (let [acc (make-account-secret-warn 100 'secret-password)]
      (is (= (acc 'secret-password 'withdraw 40) 60))
      (is (= (acc 'som-other-password 'deposit 50) "Incorrect password"))
      (is (= (acc 'som-other-password 'deposit 50) "Incorrect password"))
      (is (= (acc 'som-other-password 'deposit 50) "Incorrect password"))
      (is (= (acc 'som-other-password 'deposit 50) "Incorrect password"))
      (is (= (acc 'som-other-password 'deposit 50) "Incorrect password"))
      (is (= (acc 'som-other-password 'deposit 50) "Incorrect password"))
      (is (= (acc 'som-other-password 'deposit 50) "Incorrect password"))
      (is (= (acc 'som-other-password 'deposit 50) "call-the-cops"))
      (is (= (acc 'som-other-password 'deposit 50) "call-the-cops"))
      )))



(deftest make-simplified-withdraw-test
  (testing "test make simplified withdraw"
    (let [w (make-simplified-withdraw 25)]
      (is (= (w 20) 5))
      (is (= (w 10) -5)))))

(deftest make-decrementer-test
  (testing "test make decrementer"
    (let [D (make-decrementer 25)]
      (is (= (D 20) 5))
      (is (= (D 10) 15)))))

;; EX3.7

;; EX3.8


