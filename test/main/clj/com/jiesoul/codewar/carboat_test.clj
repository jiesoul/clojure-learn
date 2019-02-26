(ns com.jiesoul.codewar.carboat-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.carboat :refer [howmuch]]))

(deftest howmuch-test
  (testing "Basic test"
    (is (= (howmuch 1 100) [["M: 37" "B: 5" "C: 4"] ["M: 100" "B: 14" "C: 11"]]))
    (is (= (howmuch 2950 2950) []))
    ))
