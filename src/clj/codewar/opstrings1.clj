(ns codewar.opstrings1)

(defn rot [strng]
  (apply str (reverse strng)))

(defn selfie-and-rot [strng]
  (let [a (clojure.string/split strng #"\n")
        dot (fn [s]
              (apply str (repeat (count s) ".")))
        b (reduce #(str %1 %2 (dot %2) "\n")
                  (str (first a) (dot (first a)) "\n")
                  (rest a))
        c (apply str b)]
    (apply str (concat c (rest (rot c))))))
(defn oper [fct s]
  (fct s))

(def s "abcd\nefgh\nijkl\nmnop")