(ns com.jiesoul.codewar.triangle-type)

;;Triangle type
(defn triangle-type
  [a b c]
  (if (and (pos? a) (pos? b) (pos? c))
    (letfn [(lows-of-socsins [a b c]
              (/ (- (+ (* b b) (* c c)) (* a a)) (* 2 b c)))]
      (let [x (lows-of-socsins a b c)
            y (lows-of-socsins c a b)
            z (lows-of-socsins b c a)
            coll (conj [x y z])]
        (cond
          (some #(or (>= % 1) (<= % -1)) coll) 0
          (every? pos? coll) 1
          (some zero? coll) 2
          :else 3)))
    0))

(defn triangle-type
  [a b c]
  (let [[a b c] (sort [a b c])
        ab (+ (* a a) (* b b))
        cc (* c c)]
    (cond
      (>= c (+ a b)) 0
      (< cc ab) 1
      (= cc ab) 2
      :else 3)))