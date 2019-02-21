(ns com.jiesoul.codewar.Pangram-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.Pangram :refer [pangram?]]))

(deftest pangram?-test
  (is (= (pangram? "The quick brown fox jumps over the lazy dog.") true))
  (is (= (pangram? "Pack my box with five dozen liquor jugs.") true))
  (is (= (pangram? "Not every sentence is a a pangram. An example") false)))
