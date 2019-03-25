(ns com.jiesoul.sicp.ch02-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.sicp.ch02 :refer :all]))

(def one-half (make-rat 1 2))
(def one-third (make-rat 1 3))

(deftest rat-test
  (testing "add"
    (print-rat one-half)
    (is (= (print-rat (add-rat one-half one-third)) "5/6"))
    (is (= (print-rat (add-rat one-third one-third)) "2/3"))
    )
  (testing "mul"
    (is (= (print-rat (mul-rat one-half one-third)) "1/6")))
  )

(deftest list-ref-test
  (testing "list-ref"
    (let [square (list 1 4 9 16 25)]
      (is (= (list-ref square 3) 16)))
    ))

(deftest length-test
  (testing "length"
    (let [odds (list 1 3 5 7)]
      (is (= (len odds) 4))
      (is (= (length-iter odds) 4)))
    ))

(deftest append-test
  (testing "append-test"
    (is (= (append (list 1 2 3) (list 4 5 6)) (list 1 2 3 4 5 6)))
    ))
