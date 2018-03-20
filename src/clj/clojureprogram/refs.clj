(ns clojureprogram.refs
  (:require [clojureprogram.atoms :refer [futures wait-futures]]))

(defn charactor
  [name & {:as opts}]
  (ref (merge {:name name :items #{} :health 500}
              opts)))

(def smaug (charactor "Smaug" :health 500 :strength 400 :items (set (range 10))))
(def bilbo (charactor "Bilbo" :health 100 :strength 100))
(def gandalf (charactor "Ganalf" :health 75 :mana 750))

(defn loot
  [from to]
  (dosync
   (when-let [item (first (:items @from))]
     (alter to update-in [:items] conj item)
     (alter from update-in [:items] disj item))))

(wait-futures 1
              (while (loot smaug bilbo))
              (while (loot smaug gandalf)))

@smaug
@bilbo
@gandalf

(map (comp count :items deref) [bilbo gandalf])
(filter (:items @bilbo) (:items @gandalf))

(def x (ref 0))

(time (wait-futures 5
                    (dotimes [_ 1000]
                      (dosync (alter x + (apply + (range 1000)))))
                    (dotimes [_ 1000]
                      (dosync (alter x - (apply + (range 1000)))))))

@x

(time (wait-futures 5
                    (dotimes [_ 1000]
                      (dosync (commute x + (apply + (range 1000)))))
                    (dotimes [_ 1000]
                      (dosync (commute x - (apply + (range 1000)))))))

@x

(defn flawed-loot
  [from to]
  (dosync
   (when-let [item (first (:items @from))]
     (commute to update-in [:items] conj item)
     (commute from update-in [:items] disj item))))

(def smaug (charactor "Smaug" :health 500 :strength 400 :items (set (range 50))))
(def bilbo (charactor "Bilbo" :health 100 :strength 100))
(def gandalf (charactor "Gandalf" :health 75 :mana 750))

(wait-futures 1
              (while (flawed-loot smaug bilbo))
              (while (flawed-loot smaug gandalf)))

@smaug
@bilbo
@gandalf
(map (comp count :items deref) [bilbo gandalf])

(defn fixed-loot
  [from to]
  (dosync
   (when-let [item (first (:items @from))]
     (commute to update-in [:items] conj item)
     (alter from update-in [:items] disj item))))

(wait-futures 1
              (while (fixed-loot smaug bilbo))
              (while (fixed-loot smaug gandalf)))

(map (comp count :items deref) [bilbo gandalf])

(defn attack
  [aggressor target]
  (dosync
   (let [damage (* (rand 0.1) (:strength @aggressor))]
     (commute target update-in [:health] #(max 0 (- % damage))))))

(defn heal
  [healer target]
  (dosync
   (let [aid (* (rand 0.1) (:mana @healer))]
     (when (pos? aid)
       (commute healer update-in [:mana] - (max 5 (/ aid 5)))
       (commute target update-in [:health] + aid)))))

(def alive? (comp pos? :health))

(defn play
  [charactor action other]
  (while (and (alive? @charactor)
              (alive? @other)
              (action charactor other))
    (Thread/sleep (rand-int 50))))

(wait-futures 1
              (play bilbo attack smaug)
              (play smaug attack bilbo)
              (play gandalf heal bilbo))

(map (comp :health deref) [smaug bilbo])

(dosync
 (alter smaug assoc :health 500)
 (alter bilbo assoc :health 100))

(map (comp #(select-keys % [:name :health :mana]) deref) [smaug bilbo gandalf])

(defn- enforce-max-health
  [{:keys [name health]}]
  (fn [character-data]
    (or (<= (:health character-data) health)
        (throw (IllegalStateException. (str name " is already at max health"))))))

(defn character
  [name & {:as opts}]
  (let [cdata (merge {:name name :items #{} :health 500}
                     opts)
        cdata (assoc cdata :max-health (:health cdata))
        validators (list* (enforce-max-health name (:health cdata))
                          (:validator cdata))]
    (ref (dissoc cdata :validator)
         :validator #(every? (fn [v] (v %)) validators))))

(def bilbo (character "Bilbo" :health 100 :strength 100))

(defn unsafe
  []
  (io! (println "writing to database....")))

(dosync (unsafe))

(def x (ref (java.util.ArrayList.)))

(wait-futures 2
              (dosync (dotimes [v 5]
                        (Thread/sleep (rand-int 50))
                        (alter x #(doto % (.add v))))))

@x

(def x (ref 0))
(dosync
 @(future (dosync (ref-set x 0)))
 (ref-set x 1))

(def a (ref 0 :min-history 50 :max-history 100))
(future (dotimes [_ 500] (dosync (Thread/sleep 20) (alter a inc))))
(ref-max-history (ref "abc" :min-history 3 :max-history 30))
@(future (dosync (Thread/sleep 1000) @a))
(ref-history-count a)
