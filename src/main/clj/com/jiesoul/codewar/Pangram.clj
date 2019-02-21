(ns com.jiesoul.codewar.Pangram)

(defn pangram?
  [s]
  (->> s
    (clojure.string/upper-case)
    (map int)
    (filter #(and (>= % (int \A)) (<= % (int \Z))))
    (set)
    (count)
    (= 26)))

(pangram? "sssdllf1121....")
