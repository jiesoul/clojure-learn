(ns com.jiesoul.clojure-noob.ch03-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.clojure-noob.ch03 :refer :all]))

(deftest matching-part-test
  (testing "test"
    (is (= {:name "right-eye" :size 1}
            (matching-part {:name "left-eye" :size 1})))
    (is (= {:name "head" :size 3}
          (matching-part {:name "head" :size 3})))))

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(deftest hit-test
  (testing "test"
    (is (= (hit asym-hobbit-body-parts) {:name "right-upper-arm" :size 3}))))
