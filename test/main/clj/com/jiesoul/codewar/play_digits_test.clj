(ns com.jiesoul.codewar.play-digits-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.play-digits :refer [dig-pow]]))

(deftest dig-pow-test
  (testing "Test1"
    (is (= (dig-pow 89 1) 1))
    (is (= (dig-pow 92 1) -1))))
