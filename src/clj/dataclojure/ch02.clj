(ns dataclojure.ch02
  (:require [clojure.string :as str]
            [clj-diff.core :as diff]
            [clj-time.core :exclude [extend]]
            [clj-time.format :refer :all]
            [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [clojure.set :refer [union]]
            [protoflex.parse :as p]
            [valip.core :refer :all]
            [valip.predicates :refer :all]))

(def ^:dynamic *default-formats*
  [:date
   :date-hour-minute
   :date-hour-minute-second
   :date-hour-minute-second-ms
   :date-time
   :date-time-no-ms
   :rfc822
   "YYYY-MM-dd HH:mm"
   "YYYY-MM-dd HH:mm:ss"
   "dd/MM/YYYY"
   "YYYY/MM/dd"
   "d MM YYYY"])

(defprotocol ToFormatter
  (->formatter [fmt]))

(extend-protocol ToFormatter
  java.lang.String
  (->formatter [fmt]
    (formatter fmt))

  clojure.lang.Keyword
  (->formatter [fmt]
    (formatter fmt)))

(defn parse-or-nil
  [fmt date-str]
  (try
    (parse (->formatter fmt) date-str)
    (catch Exception ex nil)))

(defn normalize-datetime
  [date-str]
  (first (remove nil? (map #(parse-or-nil % date-str) *default-formats*))))

(normalize-datetime "2013-09-12")
(normalize-datetime "2013/09/12")
(normalize-datetime "26 Sep 2012")


(def phone-regex #"(?x)(\d{3})\d{0,2}\space.(\d{3})\D?(\d{4})")

(defn clean-us-phone
  [phone]
  (if-let [[_ area-code prefix post] (re-find phone-regex phone)]
    (str \( area-code \) prefix \- post)))

(clean-us-phone "123-456-7890")

(re-find phone-regex "123-456-7890")

(def fuzzy-max-diff 2)
(def fuzzy-percent-diff 0.1)
(def fuzzy-dist diff/edit-distance)

(defn fuzzy=
  [a b]
  (let [dist (fuzzy-dist a b)]
    (or (<= dist fuzzy-max-diff)
        (<= (/ dist (min (count a) (count b)))
            fuzzy-percent-diff))))

(defn records-match
  [key-fn a b]
  (let [kfns (if (sequential? key-fn) key-fn [key-fn])
        rfn (fn [prev next-fn] (and prev (fuzzy= (next-fn a) (next-fn b))))]
    (reduce rfn true kfns)))

(def data
  {:mulder {:given-name "Fox" :surname "Mulder"}
   :molder {:given-name "Fox" :surname "Molder"}
   :mulder2 {:given-name "fox" :surname "mulder"}
   :scully {:given-name "Dana" :surname "Scully"}
   :scully2 {:given-name "Dan" :surname "Scully"}})

(records-match [:given-name :surname]
               (data :mulder) (data :molder))


(defn lazy-read-bad-1
  [csv-file]
  (with-open [in-file (io/reader csv-file)]
    (csv/read-csv in-file)))

(lazy-read-bad-1 "data/small-sample.csv")

(defn lazy-read-bad-2
  [csv-file]
  (with-open [in-file (io/reader csv-file)]
    (doall (csv/read-csv in-file))))

(lazy-read-bad-2 "data/small-sample.csv")

(defn lazy-read-ok
  [csv-file]
  (with-open [in-file (io/reader csv-file)]
    (frequencies (map #(nth % 2) (csv/read-csv in-file)))))


(lazy-read-ok "data/small-sample.csv")

(defn lazy-read-csv
  [csv-file]
  (let [in-file (io/reader csv-file)
        csv-seq (csv/read-csv in-file)
        lazy (fn lazy [wrapped]
               (lazy-seq
                 (if-let [s (seq wrapped)]
                   (cons (first s) (lazy (rest s)))
                   (.close in-file))))]
    (lazy csv-seq)))

(lazy-read-csv "data/small-sample.csv")

(defn sample-percent
  [k coll]
  (filter (fn [_] (<= (rand) k)) coll))

(sample-percent 0.01 (range 1000))
;(count *1)

(defn rand-replace
  [m k v]
  (assoc (dissoc m (rand-nth (keys m))) k v))

(defn range-from
  [x]
  (map (partial + x) (range)))

(defn sample-count
  [k coll]
  (->> coll
       (drop k)
       (map vector (range-from (inc k)))
       (filter #(<= (rand) (/ k (first %))))
       (reduce rand-replace
               (into {} (map vector (range k) (take k coll))))
       (sort-by first)
       (map second)))

;(sample-count 10 (range 1000))

(defn words
  [text]
  (re-seq #"[a-z]+" (str/lower-case text)))

(defn train
  [feats]
  (frequencies feats))

(def n-words
  (train (words (slurp "data/big.txt"))))

(def alphabet
  "abcdefghijklmnopqrstuvwxyz")

(defn split-word
  [word i]
  [(.substring word 0 i) (.substring word i)])

(defn delete-char
  [[w1 w2]]
  (str w1 (.substring w2 1)))

(defn transpose-split
  [[w1 w2]]
  (str w1 (second w2) (first w2) (.substring w2 2)))

(defn replace-split
  [[w1 w2]]
  (let [w2-0 (.substring w2 1)]
    (map #(str w1 % w2-0) alphabet)))

(defn insert-split
  [[w1 w2]]
  (map #(str w1 % w2) alphabet))

(defn edits-1
  [word]
  (let [splits (map (partial split-word word)
                    (range (inc (count word))))
        long-splits (filter #(> (count (second %)) 1) splits)
        deletes (map delete-char long-splits)
        transposes (map transpose-split long-splits)
        replaces (mapcat replace-split long-splits)
        inserts (remove nil? (mapcat insert-split splits))]
    (set (concat deletes transposes replaces inserts))))

(defn known-edits-2
  [word]
  (set (filter (partial contains? n-words)
               (apply union
                      (map #(edits-1 %) (edits-1 word))))))

(defn known
  [word]
  (set (filter (partial contains? n-words) word)))

(defn correct
  [word]
  (let [candidate-thunks [#(known (list word))
                          #(known (edits-1 word))
                          #(known-edits-2 word)
                          #(list word)]]
    (->> candidate-thunks
         (map (fn [f] (f)))
         (filter #(> (count %) 0))
         first
         (map (fn [w] [(get n-words w 1) w]))
         (reduce (partial max-key first))
         second)))

(correct "deete")
(correct "editr")
(correct "tranpsose")
(correct "eidtor")
(correct "eidtr")

(defn <|
  [l r]
  (let [l-output (l)]
    (r)
    l-output))

(defn nl
  []
  (p/chr-in #{\newline \return}))

(defn define
  []
  (p/chr-in \>)
  (<| #(p/read-to-re #"[\n\r]+") nl))

(defn acid-code
  []
  (p/chr-in #{\A \B \C \D \E \F \G \H \I \K \L \M \N
            \P \Q \R \S \T \U \V \W \X \Y \Z \- \*}))

(defn acid-code-line
  []
  (<| #(p/multi+ acid-code) #(p/attempt nl)))

(defn fasta
  []
  (p/ws?)
  (let [dl (define)
        gls (apply str (flatten (p/multi+ acid-code-line)))]
    {:define dl, :gene-seq gls}))

(defn parse-fasta
  [input]
  (p/parse fasta input :eof false :auto-trim false))

(def user
  {:given-name "Fox"
   :surname "Mulder"
   :age 51
   :badge "JTT047101111"})

(defn number-present?
  [x]
  (and (present? (str x))
       (or (instance? Integer x)
           (instance? Long x))))

(defn valid-badge
  [n]
  (not (nil? (re-find #"[A-Z]{3}\d+" n))))

(defn validate-user
  [user]
  (validate user
            [:given-name present? "Given name required"]
            [:surname present? "Surname required"]
            [:age number-present? "Age required"]
            [:age (over 0) "Age should be positive"]
            [:age (under 150) "Age should be under 150."]
            [:badge present? "The badge number is required"]
            [:badge valid-badge "The badge number is invalid."]))

(validate-user (assoc user :age -42))