(ns com.jiesoul.codewar.longest-consec)

(defn longest-cons [strarr k]
  (let [size (count strarr)]
    (if (or (zero? size) (> k size) (<= k 0))
      ""
      (reduce #(if (> (count %2) (count %1)) %2 %1)
              (map (partial apply str) (partition k 1 strarr))))))

(defn longest-cons-fin [strarr k]
  (reduce #(if (> (count %2) (count %1)) %2 %1)
          (map #(partial apply str) (partition k 1 strarr))))

(longest-cons ["zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"] 2)