(ns com.jiesoul.clojure-noob.ch04
  (:require [clojure.set :as set]))

(defn titleize
  [topic]
  (str topic " for the Brave and true"))

(map titleize ["Hamsters" "Rangnarok"])

(seq '(1 2 3))
(seq [1 2 3])
(seq #{1 2 3})
(seq {:name "Bill Compton" :occupation "Dead mopey guy"})

(into {} (seq {:a 1 :b 2 :c 3}))

(map inc [1 2 3])
(map str ["a" "b" "c"] ["A" "B" "C"])

(def human-consumption [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})
(map unify-diet-data human-consumption critter-consumption)

(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))
(stats [3 4 10])
(stats [80 1 44 13 6])

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])
(map :real identities)

(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
  {}
  {:max 30 :min 10})

(assoc (assoc {} :max (inc 30))
  :min (inc 10))

(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
  {}
  {:human 4.1
   :critter 3.9})

(take 3 (range 1 11))
(drop 3 (range 1 11))

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(take-while #(< (:month %) 3) food-journal)
(drop-while #(< (:month %) 3) food-journal)
(take-while #(< (:month %) 4)
  (drop-while #(< (:month %) 2) food-journal))

(filter #(< (:human %) 5) food-journal)
(filter #(< (:month %) 3) food-journal)

(some #(> (:critter %) 5) food-journal)
(some #(> (:critter %) 3) food-journal)
(some #(and (> (:critter %) 3) %) food-journal)

(sort [3 2 1])
(sort-by count ["aaa" "c" "bb"])

(concat [1 2] [3 4])

(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
    (not (:has-pulse? record))
    record))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
           (map vampire-related-details social-security-numbers))))

(time (vampire-related-details 0))

(time (def mapped-details (map vampire-related-details (range 0 10000000))))

(time (first mapped-details))
(time (identify-vampire (range 0 1000000)))

(concat (take 8 (repeat "na")) ["Batman!"])

(take 3 (repeatedly (fn [] (rand-int 10))))

(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(take 10 (even-numbers))

(defn my-conj
  [target & additions]
  (into target additions))

(my-conj [0] 1 2 3)

(apply max [0 1 2])

(defn my-into
  [target additions]
  (apply conj target additions))

(my-into [0] [1 2 3])

(def add10 (partial + 10))
(add10 3)

(def add-missing-elements
  (partial conj ["water" "earth" "air"]))
(add-missing-elements "unobtainium" "adamantium")

(defn my-partial
  [partialized-fn & args]
  (fn [& more-args]
    (apply partialized-fn (into args more-args))))

(def add20 (my-partial + 20))
(add20 3)

(defn lousy-logger
  [log-level message]
  (condp = log-level
    :warn (clojure.string/lower-case message)
    :emergency (clojure.string/upper-case message)))

(def warn (partial lousy-logger :warn))
(warn "Red light ahead")

(defn identity-humans
  [scoial-security-numbers]
  (filter #(not (vampire? %))
    (map vampire-related-details scoial-security-numbers)))

(def not-vampire? (complement vampire?))
(defn identify-humans
  [social-security-numbers]
  (filter not-vampire?
    (map vampire-related-details social-security-numbers)))

(defn my-complement
  [fun]
  (fn [& args]
    (not (apply fun args))))
(def my-pos? (complement neg?))
(my-pos? 1)

(def filename "data/clojure-for-bat/suspects.csv")
(slurp filename)

(def vamp-keys [:name :glitter-index])
(defn str->int
  [str]
  (Integer. str))
(def conversions {:name          identity
                  :glitter-index str->int})
(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(convert :glitter-index "3")
(defn parse
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split-lines string)))

(parse (slurp filename))

(defn mapify
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(first (mapify (parse (slurp filename))))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(glitter-filter 3 (mapify (parse (slurp filename))))




