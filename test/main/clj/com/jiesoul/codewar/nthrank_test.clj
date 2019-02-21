(ns com.jiesoul.codewar.nthrank-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.nthrank :refer [rank]]))

(deftest rank-test
  (testing "Basic tests"
    (is  (= (rank "Addison,Jayden,Sofia,Michael,Andrew,Lily,Benjamin", [4, 2, 1, 4, 3, 1, 2], 4), "Benjamin"))
    (is  (= (rank "Elijah,Chloe,Elizabeth,Matthew,Natalie,Jayden", [1, 3, 5, 5, 3, 6], 2), "Matthew"))))
