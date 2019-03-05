(ns com.jiesoul.codewar.stockbroker-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.codewar.stockbroker :refer [balance]]))

(deftest a-test1
  (testing "test1"
    (is (= (balance "GOOG 300 542.0 B, AAPL 50 145.0 B, CSCO 250.0 29 B, GOOG 200 580.0 S")
           "Buy: 169850 Sell: 116000; Badly formed 1: CSCO 250.0 29 B ;"))))
(deftest a-test2
  (testing "test2"
    (is (= (balance "ZNGA 1300 2.66 B, CLH15.NYM 50 56.32 B, OWW 1000 11.623 B, OGG 20 580.1 B")
           "Buy: 29499 Sell: 0"))))

(deftest a-test3
  (testing "test3"
    (is (= (balance "CAP 1300 0.2 B, CLH16.NYM 50 56 S, OWW 1000 11 S, OGG 20 580.1 S")
           "Buy: 260 Sell: 11602; Badly formed 2: CLH16.NYM 50 56 S ;OWW 1000 11 S ;"))
    (is (= (balance "ZNGA 1300 2.66, CLH15.NYM 50 56.32 S, OWW 1000 11.623 S, OGG 20 580.1 S")
           "Buy: 0 Sell: 26041; Badly formed 1: ZNGA 1300 2.66 ;"))))

(deftest a-test4
  (testing "test4"
    (is (= (balance "") "Buy: 0 Sell: 0"))))
