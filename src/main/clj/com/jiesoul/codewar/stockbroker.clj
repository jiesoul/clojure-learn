(ns com.jiesoul.codewar.stockbroker
  (:require [clojure.string :as str]))

(def p #"\D+ \d+ \d+.\d+ B|S")

(defn balance [ords]
  (let [m (->> (str/split ords #",")
             (map #(str/trim %))
             (map #(str/split % #" "))
             (group-by last))
        b (get m "B" [])
        s (get m "S" [])
        f (fn [c]
            (reduce (fn [[v c] [_ q p _ :as b]]
                      (if (or (str/includes? q ".")
                              (not (str/includes? p ".")))

                        [v (conj c b)]
                        (let [q (Integer/parseInt q)
                              p (Double/parseDouble p)]
                          [(+ v (* q p)) c])))
                    [0.0 []]
                    c))
        bs (f b)
        ss (f s)]
    (str "Buy: " (Math/round (first bs)) " Sell: " (Math/round (first ss))
         (let [c (concat (second bs) (second ss))]
           (when (not-empty c)
            (str "; Badly formed " (count c) ": "
                 (reduce #(str %1 %2 " ;") "" (map #(str/join " " %) c))))))))

(defn balance1 [ords]
  (let [m (->> (str/split ords #",")
               (map #(str/trim %))
               (group-by #(re-seq p %)))]
    m))
