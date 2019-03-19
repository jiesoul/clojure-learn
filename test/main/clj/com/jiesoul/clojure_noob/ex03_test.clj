(ns com.jiesoul.clojure-noob.ex03-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.clojure-noob.ex03 :refer [dec-maker mapset]]))

(def dec9 (dec-maker 9))

(deftest dec-maker-test
  (testing "test"
    (is (= (dec9 10) 1))))

(deftest mapset-test
  (testing "test"
    (is (= (mapset inc [1 1 2 2]) #{2 3}))))
