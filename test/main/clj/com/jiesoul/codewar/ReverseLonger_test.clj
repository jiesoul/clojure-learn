(ns com.jiesoul.codewar.ReverseLonger-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.ReverseLonger :refer :all]))

(deftest reverseLonger-test
  (is (= (reverseLonger "first" "abcde") "abcdetsrifabcde" ))
  (is (= (reverseLonger "hello" "bau") "bauollehbau"))
  (is (= (reverseLonger "abcde" "fghi") "fghiedcbafghi"))
  (is (= (reverseLonger "abc" "123") "123cba123"))
  (is (= (reverseLonger "" "123") "321"))
  (is (= (reverseLonger "abc" "") "cba")))