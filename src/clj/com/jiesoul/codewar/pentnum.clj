(ns com.jiesoul.codewar.pentnum)

(defn step []
  (map #(/ (- (* 3 % %) %) 2) (range)))

(step)

(defn p-num[n]
  (let [r (/ (+ 1 (Math/sqrt (+ 1 (* 24 n)))) 6)]
    (== r (int r))))

(defn p-num [n]
  (some #{n} (step)))

(p-num 1)

(defn gp-num[n]

  )

(defn sp-num[n]
  ; Happy Coding ^_^
  )