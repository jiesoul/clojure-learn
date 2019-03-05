(ns com.jiesoul.codewar.stockbroker
  (:require [clojure.string :as str]))

(def p #"\D+ \d+ \d+.\d+ B|S")

(defn balance1 [ords]
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
         (let [c (concat (second bs) (second ss) (get m nil []))]
           (when (not-empty c)
            (str "; Badly formed " (count c) ": "
                 (reduce #(str %1 %2 " ;") "" (map #(str/join " " %) c))))))))

(def s "ZNGA 1300 2.66, CLH15.NYM 50 56.32 S, OWW 1000 11.623 S, OGG 20 580.1 S")

(defn balance [ords]
  (let [f (fn [v]
            (let [c (str/split v #" ")]
              (Math/round (* (Integer/parseInt (second c)) (Double/parseDouble (nth c 2))))))
        m (->> (str/split ords #",")
               (map #(str/trim %))
               (reduce (fn [[b s r] strng]
                         (let [bp  #"\D+ (\d+) (\d+.\d+) B"
                               sp  #"\D+ (\d+) (\d+.\d+) S"]
                           (cond
                             (re-seq bp strng) [(+ b (f strng)) s r]
                             (re-seq sp strng) [b (+ s (f strng)) r]
                             :else [b s (when-not (empty? strng) (conj r strng))])))
                       [0 0 []]))]
    (str "Buy: " (first m) " Sell: " (second m)
         (let [c (last m)
               l (count c)]
           (when-not (empty? c)
             (str "; Badly formed " l ": " (str/join " ;" c) " ;"))))))
