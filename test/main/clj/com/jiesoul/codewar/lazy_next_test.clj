(ns com.jiesoul.codewar.lazy-next-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.lazy-next :refer [next-item]]))

(deftest next-item-test
  (is (= (next-item (range 1 10000) 7) 8)))
