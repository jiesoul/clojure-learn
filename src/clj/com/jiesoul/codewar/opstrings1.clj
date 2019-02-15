(ns com.jiesoul.codewar.opstrings1)

(defn split-str [strng]
  (let [xs (clojure.string/split strng #"\n")
        step (fn [r coll]
               (if (empty? (first coll))
                 r
                 (recur (conj r (map first coll)) (map rest coll))))]
    (step [] xs)))

(defn join-str [coll]
  (apply str (reduce #(concat %1 ["\n"] %2) (first coll) (rest coll))))

(defn rot-90-clock [strng]
  (->> strng
       split-str
       (map reverse)
       join-str)
  )
(defn diag-1-sym [strng]
  (->> strng
       split-str
       join-str))
(defn selfie-and-diag1 [strng]
  (->> strng
       split-str
       (map #(concat %1 ["|"] %2) (clojure.string/split strng #"\n"))
       join-str)
  )
(defn oper [fct s]
  (fct s))