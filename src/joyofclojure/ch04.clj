(ns joyofclojure.ch04)

(defn pour [lb ub]
  (cond
    (= ub :toujours) (iterate inc lb)
    :else
    (range lb ub))) 


