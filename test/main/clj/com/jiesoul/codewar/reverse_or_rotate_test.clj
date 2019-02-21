(ns com.jiesoul.codewar.reverse-or-rotate-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.reverse-or-rotate :refer :all]))

(defn test-assert [act exp]
  (is (= act exp)))

(deftest revrot-test
  (testing "revrot"
    (test-assert (revrot "1234" 0) "")
    (test-assert (revrot  "" 0) "")
    (test-assert (revrot "1234", 5), "")
    (test-assert (revrot "733049910872815764", 5), "330479108928157")
    (test-assert (revrot "123456987654", 6), "234561876549")
    (test-assert (revrot "123456987653", 6), "234561356789")
    (test-assert (revrot "66443875", 4), "44668753")
    (test-assert (revrot "66443875", 8), "64438756")
    (test-assert (revrot "664438769", 8), "67834466")
    (test-assert (revrot "563000655734469485", 4), "0365065073456944")
    ))