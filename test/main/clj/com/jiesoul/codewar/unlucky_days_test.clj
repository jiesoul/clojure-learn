(ns com.jiesoul.codewar.unlucky-days-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.unlucky-days :refer :all]))


(deftest unlucky-days-test
  (is (= (unluckyDays 2015) 3))
  (is (= (unluckyDays 1986) 1)))