(ns com.jiesoul.sicp.ex01-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.sicp.ex01 :refer :all]))

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
