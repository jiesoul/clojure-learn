(ns com.jiesoul.codewar.moves-in-squared-strings-3-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.moves-in-squared-strings-3
             :refer [diag-1-sym rot-90-clock selfie-and-diag1 oper
                     diag-1-sym-1 rot-90-clock-1 selfie-and-diag1-1]]
            [clojure.string :as str]))

(def s "abcd\nefgh\nijkl\nmnop")

(deftest diag-1-sym-test
  (testing "diag-l-sym test"
    (is (= (diag-1-sym-1 s) "aeim\nbfjn\ncgko\ndhlp"))))

(deftest rot-90-clock-test
  (testing "rot-90-clock test"
    (is (= (rot-90-clock-1 s) "miea\nnjfb\nokgc\nplhd"))))

(deftest selfie-and-diag1-test
  (testing "selfie-and diagl testing"
    (is (= (selfie-and-diag1-1 s) "abcd|aeim\nefgh|bfjn\nijkl|cgko\nmnop|dhlp"))))

(defn test-assert [act exp]
  (is (= act exp)))

(deftest b-test1
  (testing "rot-90-clock"
    (test-assert(oper rot-90-clock "rgavce\nvGcEKl\ndChZVW\nxNWgXR\niJBYDO\nSdmEKb"),
                "Sixdvr\ndJNCGg\nmBWhca\nEYgZEv\nKDXVKc\nbORWle")
    ))

(deftest b-test3
  (testing "diag-1-sym"
    (test-assert(oper diag-1-sym "wuUyPC\neNHWxw\nehifmi\ntBTlFI\nvWNpdv\nIFkGjZ"),
                "weetvI\nuNhBWF\nUHiTNk\nyWflpG\nPxmFdj\nCwiIvZ")
    ))

(deftest b-test5
  (testing "selfie-and-diag1"
    (test-assert(oper selfie-and-diag1 "NJVGhr\nMObsvw\ntPhCtl\nsoEnhi\nrtQRLK\nzjliWg"),
                "NJVGhr|NMtsrz\nMObsvw|JOPotj\ntPhCtl|VbhEQl\nsoEnhi|GsCnRi\nrtQRLK|hvthLW\nzjliWg|rwliKg")
    ))
