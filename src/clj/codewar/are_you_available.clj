(ns codewar.are-you-available)

(defn check-availability [schedule current-time]
  (let [step (fn [t]
               (let [t (map #(Integer/parseInt %) (clojure.string/split t #":"))]
                 (+ (* (first t) 60) (second t))))
        cu (step current-time)
        list (filter #(and (< (step (first %)) cu) (< cu (step (second %)))) schedule)]
    (if (empty? list)
      true
      (second (first list)))))