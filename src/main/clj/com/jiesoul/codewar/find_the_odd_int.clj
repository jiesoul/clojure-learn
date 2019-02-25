(ns com.jiesoul.codewar.find-the-odd-int)

(defn find-odd [xs]
  (ffirst (filter #(odd? (second %)) (frequencies xs))))
