(ns com.jiesoul.codewar.num-move)

(defn num-move [n index]
  (let [s (str n)]
    (str (subs s 0 index) (subs s (inc index) (count s)) (subs s index (inc index)))))
