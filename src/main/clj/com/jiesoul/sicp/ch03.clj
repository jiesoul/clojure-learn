(ns com.jiesoul.sicp.ch03)

(def balance (ref 100))

(defn withdraw [amount]
  (if (>= balance amount)
    (do
      (ref-set balance (- balance amount))
      balance)
    "Insufficient funds"))

;(withdraw 50)