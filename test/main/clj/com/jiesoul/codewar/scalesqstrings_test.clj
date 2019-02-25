(ns com.jiesoul.codewar.scalesqstrings-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.scalesqstrings :refer [scale]]))

(defn test-assert [act exp]
  (is (= act exp)))

(deftest scale-test
  (testing "scale"
    (def a "abcd\nefgh\nijkl\nmnop")
    (def r (str "aabbccdd\naabbccdd\naabbccdd\neeffgghh\neeffgghh\neeffgghh\niijjkkll\niijjkkll\niijjkkll\n"
             "mmnnoopp\nmmnnoopp\nmmnnoopp"))
    (test-assert (scale a 2 3) r)
    (test-assert(scale "", 5, 5), "")
    (test-assert(scale "Kj\nSH", 1, 2),"Kj\nKj\nSH\nSH")
    ))
