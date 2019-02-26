(ns com.jiesoul.codewar.easyline-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.easyline :refer [easy-line]]))


(defn test-assert [act exp]
  (is (= act exp)))

(deftest easy-line-test
  (testing "Basic test easy-line"
    (test-assert(easy-line 7) 3432N)
    (test-assert(easy-line 13) 10400600N)
    (test-assert(easy-line 17) 2333606220N)
    (test-assert(easy-line 19) 35345263800N)
    ))
