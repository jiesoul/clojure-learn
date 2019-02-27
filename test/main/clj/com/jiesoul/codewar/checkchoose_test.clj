(ns com.jiesoul.codewar.checkchoose-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.checkchoose :refer [checkchoose]]))

(deftest a-test1
  (testing "Smaller numbers"
    (is (= (checkchoose 35N 7) 3))
    (is (= (checkchoose 36N 7) -1))
    (is (= (checkchoose 21N 21) 1))))
(deftest a-test2
  (testing "Bigger numbers"
    (is (= (checkchoose 566196948064697546150348674880N 133) 30))
    (is (= (checkchoose 4751621214095169993709641280N 129) 27))))
