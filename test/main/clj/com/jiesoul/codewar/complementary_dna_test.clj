(ns com.jiesoul.codewar.complementary-dna-test
  (:require [clojure.test :refer :all])
  (:use midje.sweet)
  (:require [com.jiesoul.codewar.complementary-dna :refer :all]))

(deftest dna-strand-test
  (is (= (dna-strand "ATCG") "TAGC")))