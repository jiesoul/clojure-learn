(ns com.jiesoul.codewar.sumdivsq-test
  (:use [midje.sweet])
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.sumdivsq :refer [list-squared]]))

(deftest list-squared-test1
  (testing "test1"
    (is (= (list-squared 1 250) [[1, 1], [42, 2500], [246, 84100]]))))
(deftest list-squared-test2
  (testing "test2"
    (is (= (list-squared 42 250) [[42, 2500], [246, 84100]]))))
(deftest list-squared-test3
  (testing "test3"
    (is (= (list-squared 250 500) [[287, 84100]]))))

(fact
  (list-squared 1800 2000) => [[1880 4884100]])