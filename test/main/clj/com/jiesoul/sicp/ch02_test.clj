(ns com.jiesoul.sicp.ch02-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.sicp.ch02 :refer :all]))

(def one-half (make-rat 1 2))
(def one-third (make-rat 1 3))

(deftest rat-test
  (testing "add"
    (print-rat one-half)
    (is (= (print-rat (add-rat one-half one-third)) "5/6"))
    (is (= (print-rat (add-rat one-third one-third)) "2/3"))
    )
  (testing "mul"
    (is (= (print-rat (mul-rat one-half one-third)) "1/6")))
  )

(deftest list-ref-test
  (testing "list-ref"
    (let [square (list 1 4 9 16 25)]
      (is (= (list-ref square 3) 16)))
    ))

(deftest length-test
  (testing "length"
    (let [odds (list 1 3 5 7)]
      (is (= (len odds) 4))
      (is (= (length-iter odds) 4)))
    ))

(deftest append-test
  (testing "append-test"
    (is (= (append (list 1 2 3) (list 4 5 6)) (list 1 2 3 4 5 6)))
    ))

(deftest scale-list-test
  (testing "scale-list-test"
    (is (= (scale-list (list 1 2 3 4 5) 10) '(10 20 30 40 50)))
    ))

(deftest map-1-test
  (testing "map-1-test"
    (is (= (map-1 #(Math/abs %) '(-10 2.5 -11.6 17)) '(10 2.5 11.6 17)))
    (is (= (map-1 #(* % %) '(1 2 3 4)) '(1 4 9 16)))
    ))

(deftest new-scale-list-test
  (testing "new-scale-list-test"
    (is (= (new-scale-list (list 1 2 3 4 5) 10) '(10 20 30 40 50)))
    ))

(deftest x-test
  (is (= (len x) 3))

  )

(deftest count-leaves-test
  (testing "count-leaves-test"
    (is (= (count-leaves x) 4))
    (is (= (count-leaves (list x x)) 8))
    ))

(deftest scale-tree-test
  (testing "scale-tree-test"
    (is (= (scale-tree '(1 (2 (3 4) 5) (6 7)) 10) '(10 (20 (30 40) 50) (60 70))))
    (is (= (scale-tree-map '(1 (2 (3 4) 5) (6 7)) 10) '(10 (20 (30 40) 50) (60 70))))
    ))

(deftest sum-odd-squares-test
  (testing "sum-oddd-squares-test"
    (is (= (sum-odd-squares '(1 (2 (3 4) 5) (6 7))) 84))
    (is (= (sum-odd-squares-1 '(1 (2 (3 4) 5) (6 7))) 84))
    ))

(deftest filter-1-test
  (testing "filter-1-test"
    (is (= (filter-1 odd? '(1 2 3 4 5)) '(1 3 5)))))

(deftest accumulate-test
  (testing "accumulate-test"
    (is (= (accumulate + 0 '(1 2 3 4 5)) 15))
    (is (= (accumulate * 1 '(1 2 3 4 5)) 120))
    (is (= (accumulate cons nil '(1 2 3 4 5)) '(1 2 3 4 5)))
    ))

(deftest enumerate-tree-test
  (testing "enumerate-tree-test"
    (is (= (enumerate-tree '(1 (2 (3 4)) 5)) '(1 2 3 4 5)))
    ))

(deftest list-fib-squares-test
  (testing "list-fib-squares"
    (is (= (list-fib-squares 10) '(0 1 1 4 9 25 64 169 441 1156 3025)))))

(deftest product-of-squares-of-odd-elements-test
  (testing "product-of-squares-of-odd-elements-test"
    (is (= (product-of-squares-of-odd-elements '(1 2 3 4 5)) 225))))

(deftest prime-sum-pairs-test
  (testing "prime sum pairs"
    (is (= (prime-sum-pairs 6) '((2 1 3) (3 2 5) (4 1 5) (4 3 7) (5 2 7) (6 1 7) (6 5 11))))
    ))

(deftest permutations-test
  (testing "permutations"
    (is (= (permutations '(1 2 3)) '((1 2 3))))
    ))

(deftest deriv-test
  (testing "deriv test"
    (is (= (sum? '(+ 1 0)) true))
    (is (= (deriv '(+ x 3) 'x) 1))
    (is (= (deriv '(* x y) 'x) 'y))
    (is (= (deriv '(* (* x y) (+ x 3)) 'x) '(+ (* x y) (* y (+ x 3)))))
    ))
