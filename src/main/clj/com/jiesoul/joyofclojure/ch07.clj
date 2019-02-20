(ns com.jiesoul.joyofclojure.ch07)

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

(defn ^:private ^:dynamic sum [nums])

(sort [1 5 7 0 -42 13])
(sort ["z" "x" "a" "aa"])
(sort [(java.util.Date.) (java.util.Date. 100)])
(sort [[1 2 3], [-1, 0, 1], [3 2 1]])

(sort > [7 1 4])
;(sort ["z" "x" "a" "aa" 1 5 8])
(sort [{:age 99}, {:age 13}, {:age 7}])
(sort [[:a 7], [:c 13], [:b 21]])
(sort second [[:a 7], [:c 13], [:b 21]])
(sort-by second [[:a 7], [:c 13], [:b 21]])
(sort-by str ["z" "x" "a" "aa" 1 5 8])
(sort-by :age [{:age 99}, {:age 13}, {:age 7}])

(def plays [{:band "Burial", :plays 979, :loved 9}
            {:band "Eno", :plays 2333, :loved 15}
            {:band "Bill Evans", :plays 979, :loved 9}
            {:band "Magma", :plays 2665, :loved 31}])

(def sort-by-loved-ratio (partial sort-by #(/ (:plays %) (:loved %))))
(sort-by-loved-ratio plays)

(defn columns [column-names]
  (fn [row]
    (vec (map row column-names))))
(sort-by (columns [:plays :loved :band]) plays)
(columns [:plays :loved :band])
((columns [:plays :loved :band])
  {:band "Burial", :plays 979, :loved 9})

(def times-two
  (let [x 2]
    (fn [y] (* x y))))

(times-two 5)

(def add-and-get
  (let [ai (java.util.concurrent.atomic.AtomicInteger.)]
    (fn [y] (.addAndGet ai y))))

(add-and-get 7)

(defn keys-apply [f ks m]
  (let [only (select-keys m ks)]
    (zipmap (keys only)
            (map f (vals only)))))
(keys-apply #(.toUpperCase %) #{:band} (plays 0))

(defn slope
  [& {:keys [p1 p2] :or {p1 [0 0] p2 [1 1]}}]
  (float (/ (- (p2 1) (p1 1))
            (- (p2 0) (p1 0)))))

(slope :p1 [4 15] :p2 [3 21])
(slope :p2 [2 1])
(slope)

(defn slope1 [p1 p2]
  {:pre [(not= p1 p2) (vector? p1) (vector? p2)]
   :post [(float? %)]}
  (/ (- (p2 1) (p1 1))
     (- (p2 0) (p1 0))))

(slope1 [10 10] [10 10])
(slope1 [10 1] '(10 10))
(slope1 [10 1] [1 20])
(slope1 [10.0 1] [1 20])


(defn put-things [m]
  (into m {:meat "beef" :veggie "broccoil"}))

(put-things {})

(defn vegan-constraints [f m]
  {:pre [(:viggie m)]
   :post [(:viggie %) (nil? (:meat %))]}
  (f m))
(vegan-constraints put-things {:viggie "carrot"})

(defn balanced-diet [f m]
  {:post [(:meat %) (:veggie %)]}
  (f m))

(balanced-diet put-things {})

(defn finicky [f m]
  {:post [(= (:meat %) (:meat m))]}
  (f m))

(finicky put-things {:meat "chicken"})

(def times-two
  (let [x 2]
    (fn [y] (* x y))))

(times-two 5)

(def add-and-get
  (let [ai (java.util.concurrent.atomic.AtomicInteger.)]
    (fn [y] (.addAndGet ai y))))

(add-and-get 2)
(add-and-get 7)

(defn times-n [n]
  (let [x n]
    (fn [y] (* x y))))

(times-n 2)

(def times-four (times-n 4))
(times-four 10)

(defn divisible [denom]
  (fn [num]
    (zero? (rem num denom))))

((divisible 3) 6)
((divisible 4) 3)

(filter even? (range 10))
(filter (divisible 4) (range 10))
(defn filter-divisible [denom s]
  (filter (fn [num] (zero? (rem num denom))) s))
(filter-divisible 4 (range 10))
(defn filter-divisible [denom s]
  (filter #(zero? (rem % denom)) s))
(filter-divisible 5 (range 10))

(def bearings [{:x 0, :y 1}
               {:x 1, :y 0}
               {:x 0, :y -1}
               {:x -1, :y 0}])

(defn forward [x y bearing-num]
  [(+ x (:x (bearings bearing-num)))
   (+ y (:y (bearings bearing-num)))])

(forward 5 5 0)
(forward 5 5 1)
(forward 5 5 2)

(defn bot [x y bearing-num]
  {:coords [x y]
   :bearing ([:north :east :south :west] bearing-num)
   :forward (fn [] (bot (+ x (:x (bearings bearing-num)))
                        (+ y (:y (bearings bearing-num)))
                        bearing-num))})

(:coords (bot 5 5 0))
(:bearing (bot 5 5 0))

(defn bot [x y bearing-num]
  {:coords [x y]
   :bearing ([:north :east :south :west] bearing-num)
   :forward (fn [] (bot (+ x (:x (bearings bearing-num)))
                        (+ y (:y (bearings bearing-num))) bearing-num))
   :turn-right (fn [] (bot x y (mod (+ 1 bearing-num) 4)))
   :turn-left (fn [] (bot x y (mod (- 1 bearing-num) 4)))})

(:bearing ((:forward ((:forward ((:turn-right (bot 5 5 0))))))))
(:coords ((:forward ((:forward ((:turn-right (bot 5 5 0))))))))

(defn mirror-bot [x y bearing-num]
  {:coords [x y]
   :bearing ([:north :east :south :west] bearing-num)
   :forward (fn [] (mirror-bot (- x (:x (bearings bearing-num)))
                               (- y (:y (bearings bearing-num)))
                               bearing-num))
   :turn-right (fn [] (mirror-bot x y (mod (- 1 bearing-num) 4)))
   :turn-left (fn [] (mirror-bot x y (mod (+ 1 bearing-num) 4)))})


(defn pow [base exp]
  (if (zero? exp)
    1
    (* base (pow base (dec exp)))))
(pow 2 10)
(pow 1.01 925)
(pow 2 100000)

(defn pow [base exp]
  (letfn [(kapow [base exp acc]
            (if (zero? exp)
              acc
              (recur base (dec exp) (* base acc))))]
    (kapow base exp 1)))
(pow 2N 10000)

(def simple-metric {:meter 1
                    :km 1000
                    :cm 1/100
                    :mm [1/10 :cm]})

(-> (* 3 (:km simple-metric))
    (+ (* 10 (:meter simple-metric)))
    (+ (* 80 (:cm simple-metric)))
    (+ (* (:cm simple-metric)
          (* 10 (first (:mm simple-metric)))))
    float)

(defn convert [context descriptor]
  (reduce (fn [result [mag unit]]
            (+ result
               (let [val (get context unit)]
                 (if (vector? val)
                   (* mag (convert context val))
                   (* mag val)))))
          0
          (partition 2 descriptor)))

(convert simple-metric [1 :meter])
(convert simple-metric [50 :cm])
(convert simple-metric [100 :mm])
(float (convert simple-metric [3 :km 10 :meter 80 :cm 10 :mm]))
(convert {:bit 1, :byte 8, :nibble [1/2 :byte]} [32 :nibble])

(defn gcd [x y]
  (int
    (cond
      (> x y) (recur (- x y) y)
      (< x y) (recur x (- y x))
      :else x)))

(defn elevator [commands]
  (letfn
    [(ff-open [[_ & r]]
       "When the elevator is open on the 1sh floor
        it can either close or be done."
       #(case _
          :close (ff-closed r)
          :done true
          false))
     (ff-closed [[_ & r]]
       #(case _
          :open (ff-open r)
          :up (sf-closed r)
          false))
     (sf-closed [[_ & r]]
       #(case _
          :down (ff-closed r)
          :open (sf-open r)
          false))
     (sf-open [[_ & r]]
       #(case _
          :close (sf-closed r)
          :done true
          false))]
    (trampoline ff-open commands)))

(elevator [:close :open :close :up :open :open :done])

(defn fac-cps [n k]
  (letfn [(cont [v] (k (* v n)))]
    (if (zero? n)
      (k 1)
      (recur (dec n) cont))))

(defn fac [n]
  (fac-cps n identity))

(fac 10)

(defn mk-cps [accept? kend kont]
  (fn [n]
    ((fn [n k]
       (let [cont (fn [v]
                    (k ((partial kont v) n)))]
         (if (accept? n)
           (k 1)
           (recur (dec n) cont))))
     n kend)))

(def fac
  (mk-cps zero?
          identity
          #(* %1 %2)))
(fac 10)

(def tri
  (mk-cps #(== 1 %)
          identity
          #(+ %1 %2)))
(tri 10)

(def world [[ 1 1 1 1 1]
            [999 999 999 999 1]
            [ 1 1 1 1 1]
            [ 1 999 999 999 999]
            [ 1 1 1 1 1]])

(defn estimate-cost [step-cost-est size y x]
  (* step-cost-est
     (- (+ size size) y x 2)))

(estimate-cost 900 5 0 0)
(estimate-cost 900 5 4 4)

(defn path-cost [node-cost cheapest-nbr]
  (+ node-cost
     (or (:cost cheapest-nbr) 0)))

(path-cost 900 {:cost 1})

(defn total-cost [newcost step-cost-est size y x]
  (+ newcost
     (estimate-cost step-cost-est size y x)))

(total-cost 0 900 5 0 0)
(total-cost 1000 900 5 3 4)
(total-cost (path-cost 900 {:cost 1}) 900 5 3 4)

(defn min-by [f coll]
  (when (seq coll)
    (reduce (fn [min other]
              (if (> (f min) (f other))
                other
                min))
            coll)))

(min-by :cost [{:cost 100} {:cost 36} {:cost 9}])

(defn neighbors [size yx]
  )

(defn astar [start-yx step-est cell-costs]
  (let [size (count cell-costs)]
    (loop [steps 0
           routes (vec (replicate size (vec (replicate size nil))))
           work-todo (sorted-set [0 start-yx])]
      (if (empty? work-todo)
        [(peek (peek routes)) :steps steps]
        (let [[_ yx :as work-item] (first work-todo)
              rest-work-todo (disj work-todo work-item)
              nbr-yxs (neighbors size yx)
              cheapest-nbr (min-by :cost
                                   (keep #(get-in routes %)
                                         nbr-yxs))
              newcost (path-cost (get-in cell-costs yx)
                                 cheapest-nbr)
              oldcost (:cost (get-in routes yx))]
          (if (and oldcost (>= newcost oldcost))
            (recur (inc steps) routes rest-work-todo)
            (recur (inc steps)
                   (assoc-in routes yx
                             {:cost newcost
                              :yxs (conj (:yxs cheapest-nbr [])
                                         yx)})
                   (into rest-work-todo
                         (map
                           (fn [w]
                             (let [[y x] w]
                               [(total-cost newcost step-est size y x) w]))
                           nbr-yxs)))))))))



