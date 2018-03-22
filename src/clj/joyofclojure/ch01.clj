(ns joyofclojure.ch01)

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
