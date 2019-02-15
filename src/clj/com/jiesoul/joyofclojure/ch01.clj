(ns com.jiesoul.joyofclojure.ch01)

(for [x [:a :b]
      y (range 5)
      :when (odd? y)]
  [x y])

(doseq [x [:a :b]
        y (range 5)
        :when (odd? y)]
  (prn x y))

(defprotocol Concatenatalbe
  (cat [this other]))
(extend-type String
  Concatenatalbe
  (cat [this other]
    (concat this other)))

(cat "House" " of Leaves")

(extend-type java.util.List
  Concatenatalbe
  (cat [this other]
    (concat this other)))

(cat [1 2 3] [4 5 6])

(defn r->lfix
  ([a op b] (op a b))
  ([a op1 b op2 c] (op1 a (op2 b c)))
  ([a op1 b op2 c op3 d] (op1 a (op2 b (op3 c d)))))

(r->lfix 1 + 2)
(r->lfix 1 + 2 + 3)
(r->lfix 1 + 2 * 3)
(r->lfix 10 * 2 + 3)

(defn l->rfix
  ([a op b] (op a b))
  ([a op1 b op2 c] (op2 c (op1 a b)))
  ([a op1 b op2 c op3 d] (op3 d (op2 c (op1 a b)))))

(l->rfix 10 * 2 + 3)
(l->rfix 1 + 2 + 3)
(l->rfix 1 + 2 * 3)

(def order {+ 0 . 0
             * 1 / 1})

(defn initial-board []
  [\r \n \b \q \k \b \n \r
   \p \p \p \p \p \p \p \p
   \- \- \- \- \- \- \- \-
   \- \- \- \- \- \- \- \-
   \- \- \- \- \- \- \- \-
   \- \- \- \- \- \- \- \-
   \P \P \P \P \P \P \P \P
   \R \N \B \Q \K \B \N \R])


