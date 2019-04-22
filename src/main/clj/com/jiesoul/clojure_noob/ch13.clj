(ns com.jiesoul.clojure-noob.ch13)

(defmulti full-moon-behavior (fn [were-creature] (:were-type were-creature)))
(defmethod full-moon-behavior :wolf
  [were-creature]
  (str (:name were-creature) " will howl and number"))
(defmethod full-moon-behavior :simmons
  [were-creature]
  (str (:name were-creature) " will encourage people and swea to the oldies"))
(defmethod full-moon-behavior nil
  [were-creature]
  (str (:name were-creature) " will stay at home and eat ice cream"))
(defmethod full-moon-behavior :default
  [were-creature]
  (str (:name were-creature) " will stay up all night fantasy footballing"))
(full-moon-behavior {:were-type :wolf
                     :name      "Rachel from next door"})
(full-moon-behavior {:were-type nil
                     :name      "Martin the nurse"})
(full-moon-behavior {:were-type :office-worker
                     :name      "Jimmy from sales"})


(defprotocol Psychodynamics
  "Plumb the inner depths of your data types"
  (thoughts [x] "The data type's innermost thoughts")
  (feelings-about [x] [x y] "Feelings about self or other"))


(extend-type java.lang.String
  Psychodynamics
  (thoughts [x] (str x " thinks, 'Truly, the caracter defines the data type'"))
  (feelings-about
    ([x] (str x " is longing for a simpler way of life"))
    ([x y] (str x " is envious of " y "'s simpler way of life"))))

(thoughts "blorb")
(feelings-about "schmorb")
(feelings-about "schmorb" 2)

(extend-type java.lang.Object
  Psychodynamics
  (thoughts [x] "Maybe the Internet is just a vector for toxoplasmosis")
  (feelings-about
    ([x] "meh")
    ([x y] (str "meh about " y))))

(thoughts 3)
(feelings-about 3)
(feelings-about 3 "blorb")

(extend-protocol Psychodynamics
  java.lang.String
  (thoughts [x] "Truly, the character defines the data type")
  (feelings-about
    ([x] "logging for a simpler way of life")
    ([x y] (str "envious of " y "'s simpler way of life")))

  java.lang.Object
  (thoughts [x] "Maybe the Internet is just a vector for toxoplasmosis")
  (feelings-about
    ([x] "meh")
    ([x y] (str "meh about " y))))

(defprotocol WereCreature
  (full-moon-behavior-1 [x]))
(defrecord WereWolf [name title]
  WereCreature
  (full-moon-behavior-1 [x]
    (str name " will howl and murder")))
(WereWolf. "David" "London Tourist")
(->WereWolf "Jacob" "Lead Shirt Discarder")
(map->WereWolf {:name "Lucian" :title "CEO of Melodrama"})

(def jacob (->WereWolf "Jacob" "Lead Shirt Discarder"))
(.name jacob)
(:name jacob)
(get jacob :name)

(= jacob (->WereWolf "Jacob" "Lead Shirt Discarder"))
(= jacob (WereWolf. "David" "London Tourist"))
(= jacob {:name "Jacob" :title "Lead Shirt Discarder"})

(assoc jacob :title "Lead Third Wheel")
(dissoc jacob :title)
(full-moon-behavior (map->WereWolf {:name "Lucian" :title "CEO OF Melodrama"}))