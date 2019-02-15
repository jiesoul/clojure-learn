(ns com.jiesoul.codewar.nthseries)

;Task:
;Your task is to write a function which returns the sum of following series upto nth term(parameter).
;
;Series: 1 + 1/4 + 1/7 + 1/10 + 1/13 + 1/16 +...
;Rules:
;You need to round the answer to 2 decimal places and return it as String.
;
;If the given value is 0 then it should return 0.00
;
;You will only be given Natural Numbers as arguments.
;
;Examples:
;SeriesSum(1) => 1 = "1.00"
;SeriesSum(2) => 1 + 1/4 = "1.25"
;SeriesSum(5) => 1 + 1/4 + 1/7 + 1/10 + 1/13 = "1.57"
;NOTE: In PHP the function is called series_sum().

(defn sum- [n]
  (loop [r 0
         n n]
       (if (zero? n)
         r
         (recur (+ r (/ 1 (+ 1 (* 3 (dec n))))) (dec n)))))

(def m-sum-
  (memoize sum-))

(defn fib []
  (map first (iterate (fn [[a b]] [b (+ a b)]) [0N 1N])))

(defn step []
  (map first (iterate (fn [[a b]] [(+ a (/ 1.0 (+ 1 (* 3 b)))) (+ b 1)]) [1 1])))

(defn series-sum [n]
  ; Happy Coding ^_^
  (let [step (fn []
               (map first (iterate (fn [[a b]] [(+ a (/ 1.0 (+ 1 (* 3 b)))) (+ b 1)]) [1 1])))
        format-n (fn [n] (format "%.2f" (double (/ (Math/round (double (* 100 n))) 100))))]
    (if (zero? n)
      "0.00"
      (format-n (nth (step) (dec n))))))