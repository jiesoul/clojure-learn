(ns com.jiesoul.codewar.last-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.last :as lt]))

(deftest last-test
  (testing "[1 9 13 1 99 9 9 13]"
    (is (= (lt/last [1 9 13 1 99 9 13]) 13))))
