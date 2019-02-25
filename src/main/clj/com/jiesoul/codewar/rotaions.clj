(ns com.jiesoul.codewar.rotaions)

(defn raotain [s]
  (let [c (count s)
        n (quot c 2)
        r (rem c 2)
        s (vec s)]
    (apply str (concat (subvec s (+ n r) c) (subvec s n (+ n r)) (subvec s 0 n)))))

(defn contain-all-rots [strng arr]
  (if (= strng "")
    true
    (let [s1 (set arr)
          s2 (raotain strng)]
      (and (contains? s1 strng) (contains? s1 s2)))))

(raotain "bsjq")
(raotain "Ajylvpy")
(contain-all-rots "Ajylvpy" ["Ajylvpy", "ylvpyAj", "jylvpyA", "lvpyAjy", "pyAjylv", "vpyAjyl", "ipywee"])
(raotain "QJAhQmS")