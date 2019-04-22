(ns com.jiesoul.joyofclojure.ch01)

(for [x [:a :b]
      y (range 5)
      :when (odd? y)]
  [x y])

(doseq [x [:a :b]
        y (range 5)
        :when (odd? y)]
  (prn x y))


(defn r->lfix
  ([a op b] (op a b))
  ([a op1 b op2 c] (op1 a (op2 b c))))
(r->lfix 1 + 2)
(r->lfix 1 + 2 + 3)
(r->lfix 10 * 2 + 3)                                        ;; error -> 50

(defn l->rfix
  ([a op b] (op a b))
  ([a op1 b op2 c] (op2 c (op1 a b)))
  ([a op1 b op2 c op3 d] (op3 d (op2 c (op1 a b)))))
(l->rfix 10 * 2 + 3)
(l->rfix 1 + 2 * 3)                                         ;; error -> 9

(def order {+ 0 - 0
            * 1 / 1})

(defn infix3 [a op1 b op2 c]
  (if (< (get order op1) (get order op2))
    (r->lfix a op1 b op2 c)
    (l->rfix a op1 b op2 c)))
(infix3 1 + 2 * 3)
(infix3 10 * 2 + 3)

(< (+ 1 (* 2 3))
   (* 2 (+ 1 4)))

(+ 1 2 3 4 5 6 7 8 9 10)

(def numbers [1 2 3 4 5 6 7 8 9 10])
(apply + numbers)

(< 0 1 3 9 36 42 108)
(< 0 1 3 9 36 -1000 42 108)

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

(defn initial-board []
  [\r \n \b \q \k \b \n \r
   \p \p \p \p \p \p \p \p
   \- \- \- \- \- \- \- \-
   \- \- \- \- \- \- \- \-
   \- \- \- \- \- \- \- \-
   \- \- \- \- \- \- \- \-
   \P \P \P \P \P \P \P \P
   \R \N \B \Q \K \B \N \R])

(def ^:dynamic *file-key* \a)
(def ^:dynamic *rank-key* \0)

(defn- file-component [file]
  (- (int file) (int *file-key*)))

(defn- rank-component [rank]
  (->> (int *rank-key*)
       (- (int rank))
       (- 8)
       (* 8)))

(defn- index [file rank]
  (+ (file-component file) (rank-component rank)))

(defn lookup [board pos]
  (let [[file rank] pos]
    (board (index file rank))))

(lookup (initial-board) "a1")

(letfn [(index [file rank]
          (let [f (- (int file) (int \a))
                r (* 8 (- 8 (- (int rank) (int \0))))]
            (+ f r)))]
  (defn lookup2 [board pos]
    (let [[file rank] pos]
      (board (index file rank)))))

(lookup2 (initial-board) "a1")

(defn lookup3 [board pos]
  (let [[file rank] (map int pos)
        [fc rc] (map int [\a \0])
        f     (- file fc)
        r     (* 8 (- 8 (- rank rc)))
        index (+ f r)]
    (board index)))

(lookup3 (initial-board) "a1")


