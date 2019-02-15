(ns com.jiesoul.codewar.arrangestring)

(defn step [simbol [first & rest]]
  (cond
    (empty? rest) [first]
    (simbol first (first rest)) ))

(defn arrange [strng]
  (let [coll (clojure.string/split strng #" ")]
    coll))

(arrange "who hit retaining The That a we taken")