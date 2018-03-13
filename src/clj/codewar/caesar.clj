(ns codewar.caesar)

(def u "I should have known that you would have a perfect answer for me!!!")
(def v ["J vltasl rlhr ", "zdfog odxr ypw", " atasl rlhr p ", "gwkzzyq zntyhv", " lvz wp!!!"])

(defn char-shift
  [c n]
  (let [d (int c)
        m (+ d n)
        a (int \a)
        z (int \z)
        A (int \A)
        Z (int \Z)]
    (cond
      (and (>= d A) (<= d Z)) (char (if (> m Z) (- m 26) m))
      (and (>= d a) (<= d z)) (char (if (> m z) (- m 26) m))
      :else
      c
      )))

(defn moving-shift [s shift]
  (let [l (count s)
        r (rem l 5)
        q (quot l 5)
        g (if (zero? r) q (inc q))
        xs (map #(char-shift %1 (mod %2 26)) (seq s) (iterate inc shift))
        xs (map #(apply str %) (partition-all g xs))]
    (if (< (count xs) 5)
      (concat xs (repeat (- 5 (count xs)) ""))
      xs)))

(defn demoving-shift [s shift]
  (let [xs (map #(char-shift %1 (- 26 (mod %2 26))) (seq (reduce str s)) (iterate inc shift))]
    (apply str xs)))

(moving-shift u 1)
(demoving-shift v 1)
(apply str '("bdfh" "jlnp" "seek" "1234"))
(moving-shift (apply str '("bdfh" "jlnp" "seek" "1234")) 1)
"I should have known that you would have a perfect answer for me!!!"
"J vltasl rlhr zdfog odxr ypw atasl rlhr p gwkzzyq zntyhv lvz wp!!!"
