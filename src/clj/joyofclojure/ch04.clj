(ns joyofclojure.ch04)

(defn pour [lb ub]
  (cond
    (= ub :toujours) (iterate inc lb)
    :else
    (range lb ub)))

(defn do-blowfish [directive]
  (case directive
    :aquarium/blowfish (println "feed the fish")
    :crypto/blowfish (println "encode the message")
    :blowfish (println "not sure what to do")))




