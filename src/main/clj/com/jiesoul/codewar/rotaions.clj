(ns com.jiesoul.codewar.rotaions
  (:require [clojure.set :refer [subset?]]))

(defn rotain [s]
  (loop [s (vec s)
         c #{}
         n (count s)]
    (if (zero? n)
      c
      (let [f (first s)
            r (rest s)
            nv (concat r [f])]
        (recur nv (conj c (apply str nv)) (dec n))))))

(defn contain-all-rots [strng arr]
  (if (= strng "")
    true
    (let [s1 (set arr)
          s2 (rotain strng)]
      (subset? s2 s1))))

(defn rotain1 [s]
  (take (count s) (iterate #(str (subs % 1) (subs % 0 1)) s)))


(rotain "bsjq")
(rotain "Ajylvpy")
(contain-all-rots "Ajylvpy" ["Ajylvpy", "ylvpyAj", "jylvpyA", "lvpyAjy", "pyAjylv", "vpyAjyl", "ipywee"])