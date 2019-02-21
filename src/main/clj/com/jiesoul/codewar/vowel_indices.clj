(ns com.jiesoul.codewar.vowel-indices)

(def vowels #{\a \e \i \o \u \y
              \A \E \I \O \U \Y})

; 某些测试不能通过
(defn vowel-indices1 [word]
  (map #(inc (key %)) (reduce-kv
                        (fn [m k v]
                          (if (contains? vowels v)
                            (assoc m k v)
                            (dissoc m k)))
                        {}
                        (vec word))))

;; 测试通过 可能提交
(defn vowel-indices [word]
  (loop [l (seq word)
         n 1
         r []]
    (if (empty? l)
      r
      (let [r (if (contains? vowels (first l))
                (conj r n)
                r)]
        (recur (rest l) (inc n) r)))))

;; 别人的方法 这是最简单的 keep-indexed 第一次知道
(defn vowel-indices2 [word]
  (keep-indexed #(when (contains? vowels %2) (inc %1)) word))

(vowel-indices "super")
(vowel-indices "YoMama")
(vowel-indices1 "super")
(vowel-indices2 "super")
(vowel-indices1 "YoMama")
(vowel-indices2 "YoMama")
