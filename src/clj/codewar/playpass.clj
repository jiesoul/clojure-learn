;Everyone knows passphrases. One can choose passphrases from poems, songs, movies names and so on but frequently they can be guessed due to common cultural references. You can get your passphrases stronger by different means. One is the following:
;
;choose a text in capital letters including or not digits and non alphabetic characters,
;
;shift each letter by a given number but the transformed letter must be a letter (circular shift),
;replace each digit by its complement to 9,
;keep such as non alphabetic and non digit characters,
;downcase each letter in odd position, upcase each letter in even position (the first character is in position 0),
;reverse the whole result.
;#Example:
;
;    your text: "BORN IN 2015!", shift 1
;
;1 + 2 + 3 -> "CPSO JO 7984!"
;
;4 "CpSo jO 7984!"
;
;5 "!4897 Oj oSpC"
;
;With longer passphrases it's better to have a small and easy program. Would you write it?
;
;https://en.wikipedia.org/wiki/Passphrase

(defn char-shift [c n]
  (let [s (int c)]
    (cond
      (and (>= s 48) (<= s 57)) (- 9 (Integer/parseInt (str c)))
      (and (>= s 65) (<= s 90)) (str (char (if (> (+ s n) 90) (- s (- 26 n)) (+ s n))))
      :else (str (char s)))))

(defn play-pass [s n]
  (let [xs (map #(conj [] (char-shift %1 n) %2) (seq s) (iterate inc 0))]
    (->> xs
         (map #(if (odd? (second %)) (clojure.string/lower-case (first %)) (first %)))
         reverse
         (apply str))))