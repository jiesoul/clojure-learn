(ns com.jiesoul.codewar.vowel-indices-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.vowel-indices :refer :all]))

(defn test-assert [act exp]
  (is (= act exp)))

(deftest test-vowel-indices
  (testing "vowel-indices"
    (test-assert (vowel-indices "super"), [2,4])
    ))