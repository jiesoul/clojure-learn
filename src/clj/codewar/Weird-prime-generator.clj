;Consider the sequence a(1) = 7, a(n) = a(n-1) + gcd(n, a(n-1)) for n >= 2:
;
;7, 8, 9, 10, 15, 18, 19, 20, 21, 22, 33, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 69, 72, 73....
;
;Let us take the differences between successive elements of the sequence and get a second sequence
; g: 1, 1, 1, 5, 3, 1, 1, 1, 1, 11, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 23, 3, 1....
;
;For the sake of uniformity of the lengths of sequences we add a 1 at the head of g:
;
;g: 1, 1, 1, 1, 5, 3, 1, 1, 1, 1, 11, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 23, 3, 1...
;
;Removing the 1s gives a third sequence: p: 5, 3, 11, 3, 23, 3... where you can see prime numbers.
;
;#Task: Write functions:
;
;1: an(n) with parameter n: returns the first n terms of the series a(n) (not tested)
;
;2: gn(n) with parameter n: returns the first n terms of the series g(n) (not tested)
;
;3: countOnes(n) with parameter n: returns the number of 1 in g(n)
;(don't forget to add a `1` at the head) # (tested)
;
;4: p(n) with parameter n: returns an array of n unique prime numbers (not tested)
;
;5: maxp(n) with parameter n: returns the biggest prime number of the sequence pn(n) # (tested)
;
;6: anOver(n) with parameter n: returns an array (n terms) of the a(i)/i for every i such g(i) != 1 (not tested but interesting result)
;
;7: anOverAverage(n) with parameter n: returns as an *integer* the average of anOver(n)  (tested)
;#Note: You can write directly functions 3:, 5: and 7:. There is no need to write functions 1:, 2:, 4: 6: except out of pure curiosity.

(defn an [n]
  (letfn [(gcd [a b]
            (if (zero? b)
              a
              (recur b (rem a b))))
          (step [n]
            (reductions #(+ %1 (gcd %2 %1)) 7 (range 2 n)))]
    (step n)))

(defn gn [n]
  (letfn [(step [n]
            (map #(- (second %) (first %)) (partition 2 1 (an (inc n)))))]
    (step n)))

(defn p [n]
  (filter #(not= % 1) (gn n)))

(defn count-ones [n]
  (count (filter #(= % 1) (cons 1 (gn n)))))

(defn max-pn [n]
  )

(defn an-over-average [n]

  )