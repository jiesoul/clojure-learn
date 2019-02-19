(ns com.jiesoul.codewar.accumule)

(defn accum [s]
  (let [list (map
              #(apply str
                      (clojure.string/upper-case %1)
                      (repeat %2 (clojure.string/lower-case %1)))
              s
              (range))]
    (clojure.string/join "-" list)))

(accum "adcd")