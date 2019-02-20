(ns com.jiesoul.codewar.calc)

;Given a string, first turn each letter into its ASCII char code.
;
;example:
;
;'ABC' --> x=65, y=66, z=67 --> '656667'
;
;    Let's call this number 'total1'.
;
;Then replace any incidence of the number 7, with the number 1.
;
;'656667' ---> '656661'
;
;    Lets call this number 'total2'.
;
;Then return the difference between the sum of the digits in total1 and total2:
;
;(6 + 5 + 6 + 6 + 6 + 7)
;- (6 + 5 + 6 + 6 + 6 + 1)
;-------------------------
;6

(defn calc [s]
  (let [xs (filter #(= % \7) (seq (apply str (map int (seq s)))))]
    (* 6 (count xs))))