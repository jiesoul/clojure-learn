(ns com.jiesoul.codewar.arge-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.arge :refer [nb-year nb-year1]]))

(defn test-assert [act exp]
  (is (= act exp)))

(deftest nb-year-test
  (testing "test"
    (test-assert (nb-year 1500, 5, 100, 5000), 15)
    (test-assert (nb-year 1500000, 2.5, 10000, 2000000), 10)
    (test-assert (nb-year 1500000, 0.25, 1000, 2000000), 94)))

(deftest nb-year-test
  (testing "test"
    (test-assert (nb-year1 1500, 5, 100, 5000), 15)
    (test-assert (nb-year1 1500000, 2.5, 10000, 2000000), 10)
    (test-assert (nb-year1 1500000, 0.25, 1000, 2000000), 94)))
