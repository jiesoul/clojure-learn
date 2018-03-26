(ns codewar.human-readable)

(defn formatDuration [secs]
  (let [seconds 1
        minutes (* seconds 60)
        hours (* 60 minutes)
        days (* hours 24)
        years (* days 365)
        mm  [[years "years"], [days "days"] [hours "hours"] [minutes "minutes"] [seconds "seconds"]]]
    (loop [result []
           n secs
           coll mm]
      (if (or (zero? n) (empty? coll))
        result
        (let [fv (first coll)
              nv (quot n (first fv))
              re (rem n (first fv))]
          (recur (if (not= nv 0) (conj result [nv (second fv)])) re (rest coll))))))
  )