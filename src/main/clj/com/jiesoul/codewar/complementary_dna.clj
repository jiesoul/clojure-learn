(ns com.jiesoul.codewar.complementary-dna)

(defn dna-strand [dna]
  (apply str
         (map #(condp = %
           \A "T"
           \T "A"
           \C "G"
           \G "C")
        dna)))
