(ns com.jiesoul.codewar.moduli-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.moduli :refer [fromNb2Str]]))

(deftest a-test1
  (testing "1st Test"
    (is (= (fromNb2Str 187 [8 7 5 3]) "-3--5--2--1-"))
    (is (= (fromNb2Str 259 [8 7 5 3]) "-3--0--4--1-"))
    (is (= (fromNb2Str 15 [8 6 5]) "-7--3--0-"))
    (is (= (fromNb2Str 15 [8 6 5 3]) "-7--3--0--0-"))
    (is (= (fromNb2Str 0 [8 7 5 3]) "-0--0--0--0-"))
    (is (= (fromNb2Str 345000 [97 31 17 13 11 7 5 3 2]) "Not applicable"))
    (is (= (fromNb2Str 446 [8 7 5 3]) "Not applicable"))

    ))
