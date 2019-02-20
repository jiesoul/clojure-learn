(ns com.jiesoul.codewar.max-rot)

(defn max-rot [n]
  ; your code
  (let [num (str n)
        cou (count num)]
    (loop [index 0
           s num
           list []]
      (if (< index (dec cou))
        (let [ss (str (subs s 0 index) (subs s (inc index) cou) (subs s index (inc index)))]
          (recur (inc index)
                 ss
                 (conj list (Long/parseLong ss))))
        (apply max (conj list n))))))
