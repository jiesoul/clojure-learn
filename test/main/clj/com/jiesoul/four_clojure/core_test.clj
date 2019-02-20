(ns com.jiesoul.four-clojure.core-test
  (:require [clojure.test :refer :all]
            [com.jiesoul.four-clojure.core :refer :all]))

(deftest pali-nums-test
  (testing "test pali nums "
    (is (= (take 26 (pali-nums 0))
        [0 1 2 3 4 5 6 7 8 9
         11 22 33 44 55 66 77 88 99
         101 111 121 131 141 151 161]))
    (is (= (take 16 (pali-nums 162))
           [171 181 191 202
            212 222 232 242
            252 262 272 282
            292 303 313 323]))
    (is (= (take 6 (pali-nums 1234550000))
           [1234554321 1234664321 1234774321
            1234884321 1234994321 1235005321]))
    (is (= (first (pali-nums (* 111111111 111111111)))
           (* 111111111 111111111)))
    (is (= (set (take 199 (pali-nums 0)))
           (set (map #(first (pali-nums %)) (range 0 10000)))))
    (is (= true
           (apply < (take 6666 (pali-nums 9999999)))))
    (is (= (nth (pali-nums 0) 10101)
           9102019))))
