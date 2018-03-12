(ns joyofclojure.ch07)

(def fifth (comp first rest rest rest rest))

(fifth [1 2 3 4 5])

(defn fnth [n]
  (apply comp (cons first (take (dec n) (repeat rest)))))

((fnth 5) '[a b c d e])

(map (comp keyword #(.toLowerCase %) name) '(a b c))

((partial + 5) 100 200)

(let [truthiness (fn [v] v)]
  [((complement truthiness) true)
   ((complement truthiness) 42)
   ((complement truthiness) false)
   ((complement truthiness) nil)])

(defn join
  {:test (fn []
           (assert
             (= (join "," [1 2 3]) "1,2,3")))}
  [seq s]
  (apply str (interpose seq s)))

(def times-two
  (let [x 2]
    (fn [y] (* x y))))

(times-two 5)

(def add-and-get
  (let [ai (java.util.concurrent.atomic.AtomicInteger.)]
    (fn [y] (.addAndGet ai y))))

(add-and-get 7)