(ns com.jiesoul.codewar.prime-decomp-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.prime-decomp :refer [prime-factors]]))

(deftest a-test1
  (testing "Test 1"
    (def ur 86240)
    (def vr "(2**5)(5)(7**2)(11)")
    (is (= (prime-factors ur) vr))))
