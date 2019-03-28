(ns com.jiesoul.sicp.ex02-test
  (:require [clojure.test :refer :all])
  (:require [com.jiesoul.sicp.ex02 :refer :all]))

(deftest make-rat-real-test
  (is (= (make-rat-real -1 2) [-1 2]))
  (is (= (make-rat-real 1 -2) [-1 2]))
  (is (= (make-rat-real -1 -2) [1 2]))
  (is (= (make-rat-real 1 2) [1 2]))

  )

(deftest midpoint-segment-test
  (testing "midpoint-segment-test"
    (is (= (midpoint-segment (make-segment (make-point 1 1) (make-point -1 -1))) [0 0]))
    (is (= (midpoint-segment (make-segment (make-point 2 1) (make-point -2 -1))) [0 0]))
    (is (= (midpoint-segment (make-segment (make-point 1 1) (make-point 3 3))) [2 2]))
    ))

(deftest make-rectangle-test
  (testing "sss"
    (is (= (make-rectangle (make-segment (make-point 1 1) (make-point -1 -1)))
           [[1 1] [1 -1] [-1 -1] [-1 1]]))
    ))

(deftest list-pair-test
  (testing "list-pair-test"
    (is (= (list-pair (list 1 34 25 34)) 34))
    ))

(deftest reverse-1-test
  (testing "reverse-1-test"
    (is (= (reverse-1 (list 1 2 3)) (list 3 2 1)))
    ))

(deftest cc-test
  (testing "cc-test"
    (is (= (cc 10 us-coins) 4))
    (is (= (cc 100 us-coins) 292))
    (is (= (cc 10 (reverse us-coins)) 4))
    (is (= (cc 100 (reverse us-coins)) 292))
    (is (= (cc 100 uk-coins) 104561))
    (is (= (cc 100 (reverse uk-coins)) 104561))
    ))

(deftest same-parity-test
  (testing "same-parity-test"
    (is (= (same-parity 1 2 3 4 5 6 7) (list 1 3 5 7)))
    ))

(deftest square-list-test
  (testing "square-list-test"
    (is (= (square-list '(1 2 3 4)) '(1 4 9 16)))
    (is (= (square-list-1 '(1 2 3 4)) '(1 4 9 16)))
    ))

(deftest square-list-iter-test
  (testing "square-list-iter-test"
    (is (not= (square-list-iter '(1 2 3 4)) '(1 4 9 16)))
    ))

(deftest for-each-test
  (testing "for-each-test"
    (for-each #(println (str %)) '(57 321 88))
    ))

(deftest test-2-25
  (testing "2.25 test"
    (is (= (first (rest (first (rest (rest '(1 3 (5 7) 9)))))) 7))
    (is (= (first (first '((7)))) 7))
    (is (= (first
             (rest
               (first
                 (rest
                   (first
                     (rest
                       (first
                         (rest
                           (first
                             (rest
                               (first
                                 (rest
                                   '(1 (2 (3 (4 (5 (6 7)))))))))))))))))) 7))
    ))

(deftest deep-reverse-test
  (testing "deep-reverse-test"
    (is (= (deep-reverse '(1 2)) '(2 1)))
    (is (= (deep-reverse '((1 2) (3 4 5))) '((5 4 3) (2 1))))
    ))

(deftest fringe-test
  (testing "fringe-test"
    (let [x (list (list 1 2) (list 3 4))]
      (is (= (fringe x) (list 1 2 3 4)))
      (is (= (fringe (list x x)) (list 1 2 3 4 1 2 3 4)))
      )
    ))

(deftest square-tree-test
  (testing "square-tree-test"
    (is (= (square-tree '(1 (2 (3 4) 5) (6 7))) '(1 (4 (9 16) 25) (36 49))))
    (is (= (square-tree-map '(1 (2 (3 4) 5) (6 7))) '(1 (4 (9 16) 25) (36 49))))
    (is (= (square-tree-f '(1 (2 (3 4) 5) (6 7))) '(1 (4 (9 16) 25) (36 49))))
    ))

(deftest subsets-test
  (testing "subsets-test"
    (is (= (subsets '(1 2 3)) '(() (3) (2) (2 3) (1) (1 3) (1 2) (1 2 3))))
    ))

(deftest append-1-test
  (testing "append-1"
    (is (= (append-1 '(1 2) '(3 4)) '(1 2 3 4)))
    ))

(deftest map-p-test
  (testing "map 1 test"
    (is (= (map-p inc '(1 2 3)) '(2 3 4)))
    ))

(deftest length-1-test
  (testing "length 1 test"
    (is (= (length-1 '(1 2 3)) 3))
    ))

(deftest horner-eval-test
  (testing "horner eval"
    (is (= (horner-eval 2 '(1)) 1))
    (is (= (horner-eval 2 '(1 3)) 7))
    (is (= (horner-eval 2 '(1 3 0)) 7))
    (is (= (horner-eval 2 '(1 3 0 5)) 47))
    (is (= (horner-eval 2 '(1 3 0 5 0)) 47))
    (is (= (horner-eval 2 '(1 3 0 5 0 1)) 79))
    (is (= (horner-eval 3 '(1)) 1))
    (is (= (horner-eval 3 '(1 3)) 10))
    (is (= (horner-eval 3 '(1 3 0)) 10))
    (is (= (horner-eval 3 '(1 3 0 5)) 145))
    ))

(deftest count-leaves-tree-test
  (testing "count leaves test"
    (is (= (count-leaves-tree '(1 2 3 4)) 4))
    (is (= (count-leaves-tree '((1 2 3 4) (1 2 3 4))) 8))
    ))

(deftest accumulate-n-test
  (testing "accumulate n test"
    (let [x '((1 2 3) (4 5 6) (7 8 9) (10 11 12))]
      (is (= (accumulate-n + 0 x) '(22 26 30))))
    ))

(deftest dot-product-test
  (testing "dot product test"

    ))

(deftest transpose-test
  (testing "transpose test"
    (let [m '((1 2 3 4) (4 5 6 6) (6 7 8 9))
          n '((3 2 1) (1 1 0))]
      (is (= (transpose m)
             '((1 4 6) (2 5 7) (3 6 8) (4 6 9))))
      (is (= (transpose n)
             '((3 1) (2 1) (1 0)))))
    ))

;(deftest matrix-*-matrix-test
;  (testing "matrix * matrix"
;    (let [m '((1 0 2) (-1 3 1))
;          n '((3 2 1) (1 1 0))]
;      (is (= (matrix-*-matrix m (transpose n))
;             '((5 1) (4 2))))
;      )
;    ))

(deftest fold-test
  (testing "fold "
    (is (= (fold-left / 1 '(1 2 3)) 1/6))
    (is (= (fold-right / 1 '(1 2 3)) 1/6))
    (is (= (fold-left list nil '(1 2 3)) '(((nil 1) 2) 3)))
    (is (= (fold-right list '() '(1 2 3)) '(((() 3) 2) 1)))
    ))
