(ns com.jiesoul.algorithms-jefffe.ch00-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.algorithms-jefffe.ch00 :refer [peasant-multi fibonacci-multiply]]))


(deftest fibonacci-multiply-test
  (testing "testing"
    (is (= 2 (fibonacci-multiply 2 9)))))


(deftest peasant-multi-test
  (testing "testing"
    (is (= 293276 (peasant-multi 934 314)))
    (is (= 56088 (peasant-multi 123 456)))))

