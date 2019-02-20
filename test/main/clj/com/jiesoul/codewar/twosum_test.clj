(ns com.jiesoul.codewar.twosum-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.twosum :refer :all]))

(deftest twosum-tests
  (is (= (sort < (twosum '[1234 5678 9012] 14690)) '[1 2]))
  )

(deftest twosum1-tests
  (is (= (sort < (twosum '[1234 5678 9012] 14690)) '[1 2])))