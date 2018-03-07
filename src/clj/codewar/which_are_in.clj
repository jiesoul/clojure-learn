(ns codewar.which-are-in)

;Given two arrays of strings a1 and a2 return a sorted array r in lexicographical order of the strings of a1 which are substrings of strings of a2.
;
;#Example 1: a1 = ["arp", "live", "strong"]
;
;a2 = ["lively", "alive", "harp", "sharp", "armstrong"]
;
;returns ["arp", "live", "strong"]
;
;#Example 2: a1 = ["tarp", "mice", "bull"]
;
;a2 = ["lively", "alive", "harp", "sharp", "armstrong"]
;
;returns []
;
;Notes:
;Arrays are written in "general" notation. See "Your Test Cases" for examples in your language.
;
;In Shell bash a1 and a2 are strings. The return is a string where words are separated by commas.
;
;Beware: r must be without duplicates.

(def ur ["olp" "love" "string"])
(def vr ["ulove" "alove" "holp" "sholp","vfmstring"])
(def rr ["love" "olp" "string"])

(defn in-array [array1 array2]
  (letfn [(part-str [n s] (map #(apply str %) (partition n 1 s)))
          (sub-array [n xs] (mapcat #(part-str n %) xs))
          (sub? [s xs] (not-empty (filter #(= s %) (sub-array (count s) xs))))]
    (sort (vec (set (filter #(sub? % array2) array1))))))