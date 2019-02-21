(ns com.jiesoul.codewar.rotaions)

(defn contain-all-rots [strng arr]
  (if (= strng "")
    true
    (let [f (fn [s]
              (let [c (count s)]
                (loop [c e
                       n 0]
                  )))]
      (contains? (set arr) strng))))
