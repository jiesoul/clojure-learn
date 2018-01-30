;John and his wife Ann have decided to go to Codewars.
;
;On day 0 Ann will do one kata and John - he wants to know how it is working - 0.
;
;Let us call a(n) the number of katas done by Ann at day n we have a(0) = 1 and in the same manner j(0) = 0.
;
;They have chosen the following rules:
;
;On day n the number of katas done by Ann should be n minus the number of katas done by John at day t, t being equal to the number of katas done by Ann herself at day n - 1.
;
;On day n the number of katas done by John should be n minus the number of katas done by Ann at day t, t being equal to the number of katas done by John himself at day n - 1.
;
;Whoops! I think they need to lay out a little clearer exactly what there're getting themselves into!
;
;Could you write:
;1) two functions ann and john (parameter n) giving the list of the numbers of katas Ann and John should take on each day from day 0 to day n - 1 (n days - see first example below)?
;2) The total number of katas taken by ann (function sum_ann(n)) and john (function sum_john(n)) from day 0 (inclusive) to day n (exclusive)?
;Examples:
;john(11) -->  [0, 0, 1, 2, 2, 3, 4, 4, 5, 6, 6]
;ann(6) -->  [1, 1, 2, 2, 3, 3]
;
;sum_john(75) -->  1720
;sum_ann(150) -->  6930
;Shell Note:
;sumJohnAndAnn has two parameters:
;
;first one : n (number of days, $1)
;
;second one : which($2) ->
;
;1 for getting John's sum
;
;2 for getting Ann's sum.
;
;See "Sample Tests".
;
;Note:
;Keep an eye on performance.

(declare j a)

(defn j [n]
  (if (zero? n)
    0
    (- n (a (j (dec n))))))

(defn a [n]
  (if (zero? n)
    1
    (- n (j (a (dec n))))))

(def j (memoize j))
(def a (memoize a))

(defn john [n]
  (take n (map j (iterate inc 0))))

(defn ann [n]
  (take n (map a (iterate inc 0))))

(defn sum-john [n]
   (reduce + (john n)))

(defn sum-ann [n]
   (reduce + (ann n)))

(defn fib []
  (map first (iterate (fn [[a b]] [b (+ a b)]) [0N 1N])))

(def lots-of-fibs (take 1000000 (fib)))

(def johns (take 150 (sum-john 150)))