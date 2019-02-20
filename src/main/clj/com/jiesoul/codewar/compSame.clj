(ns com.jiesoul.codewar.compSame)

(defn compSame [a b]
  (cond
    (and (nil? a) (nil? b)) true
    (and (nil? a) (not (nil? b))) false
    (and (nil? b) (not (nil? a))) false
    (not= (count a) (count b)) false
    (and (empty? a) (empty? b)) true
    :else
    (loop [coll (map #(* % %) a)
           r b]
      (if (empty? coll)
        (empty? r)
        (recur (rest coll) (drop-while #(not= % (first coll)) r))))))
