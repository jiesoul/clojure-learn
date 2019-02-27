(ns com.jiesoul.codewar.coding-with-squared-string)

(defn transpose [s]
  (apply map vector s))

(defn code [s]
  (if (= s "")
    s
    (let [l  (count s)
          n  (first (filter #(>= (second %) l)
                      (iterate (fn [[a _]]
                                 (let [a (inc a)]
                                   [a (* a a)]))
                        [1 1])))
          tl (- (second n) l)
          t  (str s (apply str (repeat tl (char 11))))]
      (->> t
        (partition (first n))
        (transpose)
        (map reverse)
        (map #(apply str %))
        (clojure.string/join \newline)))))

(defn decode [s]
  (->> (clojure.string/split-lines s)
    (transpose)
    (reverse)
    (map #(remove #{(char 11)} %))
    (map #(apply str %))
    (apply str)))

(def s "I.was.going.fishing.that.morning.at.ten.o'clock")
(code s)

(decode (code s))
