(ns com.jiesoul.codewar.noboringzeros)

(defn no-boring-zeros [n]
  (if (and (not (zero? n)) (zero? (rem n 10)))
    (no-boring-zeros (quot n 10))
    n))

(no-boring-zeros 100)
(no-boring-zeros 0)
