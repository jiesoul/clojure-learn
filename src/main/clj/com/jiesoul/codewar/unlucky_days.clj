(ns com.jiesoul.codewar.unlucky-days)

(defn unluckyDays [year]
  (->> (range 1 13)
    (map #(.getDayOfWeek (java.time.LocalDate/of year % 13)))
    (filter #(= % (java.time.DayOfWeek/FRIDAY)))
    (count)))

(unluckyDays 2015)
