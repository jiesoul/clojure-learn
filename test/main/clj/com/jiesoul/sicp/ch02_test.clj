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
