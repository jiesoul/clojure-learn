(ns com.jiesoul.codewar.array-diff-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.array-diff :refer [array-diff]]))

(deftest array-diff-test
  (testing "basic testing"
    (is (= (array-diff [1 2] [1]) [2]))
    (is (= (array-diff [1 2 2 2 2 3] [2]) [1, 3]))))
