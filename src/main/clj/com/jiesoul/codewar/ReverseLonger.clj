(ns com.jiesoul.codewar.ReverseLonger)

(defn reverseLonger
  [a b]
  (let [al (count a)
        bl (count b)]
    (cond
      (= al bl) (apply str b (apply str (reverse a)) b)
      (> al bl) (apply str b (apply str (reverse a)) b)
      :else (apply str a (apply str (reverse b)) a))))
