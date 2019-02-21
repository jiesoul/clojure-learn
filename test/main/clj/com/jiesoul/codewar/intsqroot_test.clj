(ns com.jiesoul.codewar.intsqroot-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.intsqroot :refer [int-rac]]))

(deftest int-rac-test
  (testing "Basic tests"
    (is (= (int-rac 125348 300) 3))
    (is (= (int-rac 25 1) 4))))
