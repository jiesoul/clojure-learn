(ns com.jiesoul.sicp.ex02-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.sicp.ex02 :refer [make-rat-real]]))

(deftest make-rat-real-test
  (is (= (make-rat-real -1 2) [-1 2]))
  (is (= (make-rat-real 1 -2) [-1 2]))
  (is (= (make-rat-real -1 -2) [1 2]))
  (is (= (make-rat-real 1 2) [1 2]))

  )
