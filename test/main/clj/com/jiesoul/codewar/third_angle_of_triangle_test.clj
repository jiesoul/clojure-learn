(ns com.jiesoul.codewar.third-angle-of-triangle-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.third-angle-of-triangle :refer [third-angle]]))

(deftest a-test1
  (testing "Test 1"
    (def a 30)
    (def b 60)
    (def c 90)
    (is (= (third-angle a b) c))))
