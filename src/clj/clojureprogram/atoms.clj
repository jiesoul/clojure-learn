(ns clojureprogram.atoms)

(defmacro futures
  [n & exprs]
  (vec (for [_ (range n)
             expr exprs]
         `(future ~expr))))

(defmacro wait-futures
  [& args]
  `(doseq [f# (futures ~@args)]
     @f#))

(def sarah (atom {:name "Sarah" :age 25 :wears-glasses? false}))

(swap! sarah update-in [:age] + 3)

(swap! sarah (comp #(update-in % [:age] inc)
                   #(assoc % :wears-glasses? true)))

(def xs (atom #{1 2 3}))

(wait-futures 1 (swap! xs (fn [v]
                            (Thread/sleep 250)
                            (println "trying 4")
                            (conj v 4)))
              (swap! xs (fn [v]
                          (Thread/sleep 500)
                          (println "trying 5")
                          (conj v 5))))

@xs

(def x (atom 2000))

(swap! x #(Thread/sleep %))

(compare-and-set! xs :wrong "new value")
(compare-and-set! xs @xs "new value")
@xs

(reset! xs :y)
@xs

(defn echo-watch
  [key identity old new]
  (println key old "=>" new))

(def sarah (atom {:name "Sarah" :age 25}))
(add-watch sarah :echo echo-watch)
(swap! sarah update-in [:age] inc)
(add-watch sarah :echo2 echo-watch)
(swap! sarah update-in [:age] inc)
(remove-watch sarah :echo2)
(swap! sarah update-in [:age] inc)
(reset! sarah @sarah)

(def history (atom ()))

(defn log->list
  [dest-atom key source old new]
  (when (not= old new)
    (swap! dest-atom conj new)))

(def sarah (atom {:name "Sarah" :age 25}))
(add-watch sarah :record (partial log->list history))

(swap! sarah update-in [:age] inc)
(swap! sarah identity)
(swap! sarah assoc :wears-glasses? true)
(swap! sarah update-in [:age] inc)
(println @histor)

(def n (atom 1 :validator pos?))
(swap! n + 500)
(swap! n - 1000)

(def sarah (atom {:name "Sarah" :age 25}))
(set-validator! sarah :age)
(swap! sarah dissoc :age)
(set-validator! sarah #(or (:age %)
                           (throw (IllegalStateException. "People must have `:age`s!"))))

(swap! sarah dissoc :age)
