(ns com.jiesoul.codewar.rotaions-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.rotaions :refer [contain-all-rots]]))

(defn test-assert [act exp]
  (is (= act exp)))

(deftest contain-all-rots-test
  (testing "contain-all-rots"
    (test-assert (contain-all-rots "bsjq" ["bsjq", "qbsj", "sjqb", "twZNsslC", "jqbs"]) true)
    (test-assert (contain-all-rots "" []) true)
    (test-assert (contain-all-rots "" ["bsjq", "qbsj"]) true)
    (test-assert (contain-all-rots "XjYABhR" ["TzYxlgfnhf", "yqVAuoLjMLy", "BhRXjYA", "YABhRXj", "hRXjYAB", "jYABhRX", "XjYABhR", "ABhRXjY"]) false)
    (test-assert (contain-all-rots "Ajylvpy" ["Ajylvpy" "ylvpyAj" "jylvpyA" "lvpyAjy" "pyAjylv" "vpyAjyl"]) false)
    (test-assert (contain-all-rots "UDvG" ["vGUD" "UDvG" "GUDv" "DvGU"]) true)
    ))
