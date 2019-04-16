(ns com.jiesoul.clojure-noob.ch03)

(+ 1 2 3)
(str "It was the panda " "in the library " "with a dust buster")
(nil? 1)
(nil? nil)

(if "bears eat beets"
  "bears beets Battlestar Galactica")

(if nil
  "This won't be the result because nil is falsey"
  "nil is falsey")

(= 1 1)
(= nil nil)
(= 1 2)
(or false nil :large_I_men_venti :why_cant_I_just_say_large)
(or (= 0 1) (= "yes" "no"))
(or nil)

(and :free_wifi :hot_coffee)
(and :feelin_super_cool nil false)

(def failed-protagonist-names
  ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])
(def severity :mild)

(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
    (if (severity :mild)
      "MIDLY INCONWENIENCED!"
      "DOOOOOMED!")))

(defn too-enthusisastic
  "Return a cheer that might be a bit too enthusiastic"
  [name]
  (str "OH. MY. GOD! " name " YOU ARE MOST DEFINITELY LIKE THE BEST"
    "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"))
(too-enthusisastic "Zelda")

(defn x-chop
  ([name chop-type]
    (str "I " chop-type " chop " name "! Take that!"))
  ([name]
    (x-chop name "karate")))

(defn my-first
  [[fist-thing]]
  fist-thing)

(my-first ["oven" "bike" "war-axe"])

(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
             "Here they are in case you need to cry over them: "
             (clojure.string/join ", " unimportant-choices))))

(chooser ["Marmalade" "Handsome Jack" "Pigpen" "Aquaman"])

(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location {:lat 28.22 :lng 81.3})

(defn announce-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(defn illustrative-function
  []
  (+ 1 305)
  30
  "joe")
(illustrative-function)

(defn number-comment
  [x]
  (if (> x 6)
    "Oh my gosh! What a big number!"
    "That number's OK, I guess"))

(number-comment 5)
(number-comment 7)

((fn [x] (* x 3)) 9)

(defn inc-maker
  [inc-by]
  #(+ % inc-by))
(def inc3 (inc-maker 3))
(inc3 7)

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
          (into final-body-parts
            (set [part (matching-part part)])))))))

(defn recursive-printer
  ([]
    (recursive-printer 0))
  ([iteration]
    (println iteration)
    (if (> iteration 3)
      (println "Goodbye!")
      (recursive-printer (inc iteration)))))

(recursive-printer)


(re-find #"^left-" "left-eye")
(re-find #"^left-" "cleft-eye")
(re-find #"^left-" "wongleblart")


(defn my-reduce
  ([f initial coll]
   (loop [result initial
          remaining coll]
     (if (empty? remaining)
       result
       (recur (f result (first remaining)) (rest remaining)))))
  ([f [head & tail]]
   (my-reduce f head tail)))

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
    []
    asym-body-parts))

(defn hit
  [asym-body-parts]
  (println asym-body-parts)
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (println body-part-size-sum target)
    (loop [[part & remaining] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))

;(hit asym-hobbit-body-parts)





