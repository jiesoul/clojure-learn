(ns com.jiesoul.codewar.accumule-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.accumule :refer [accum]]))

(deftest accum-test
  (is (= (accum "adbc") "A-Dd-Bbb-Cccc")))
