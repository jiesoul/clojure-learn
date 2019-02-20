(ns com.jiesoul.codewar.decomp-square-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.decomp-square :refer [decompose]]))

(deftest decompose-test
  (testing "test 1"
    (is (= (decompose 463) [5 30 462]))))
