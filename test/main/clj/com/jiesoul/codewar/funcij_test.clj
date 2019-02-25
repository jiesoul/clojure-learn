(ns com.jiesoul.codewar.funcij-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.funcij :refer [sumin sumax sumsum]]))

(deftest sumin-test
  (testing "sumin testing"
    (is (= (sumin 5) 55))
    (is (= (sumin 6) 91))
    (is (= (sumin 45) 31395))
    (is (= (sumin 999) 332833500))
    (is (= (sumin 5000) 41679167500))
    ))

(deftest sumax-test
  (testing "sumax testing"
    (is (= (sumax 5)) 95)
    (is (= (sumax 6)) 161)
    ))
