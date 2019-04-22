(ns com.jiesoul.joyofclojure.ch03)


(if true :truthy :falsey)
(if [] :truthy :falsey)
(if nil :truthy :falsey)
(if false :truthy :falsey)

(def evil-false (Boolean. "false"))                         ;; never do not
(= false evil-false)
(if evil-false :truthy :falsey)
(if (Boolean/valueOf "false") :truthy :falsey)

(when (nil? nil) "Actually nil, not false")

(seq [1 2 3])
(seq [])
(defn print-seq [s]
  (when (seq s)
    (prn (first s))
    (recur (rest s))))
(print-seq [])

(def guys-whole-name ["Guy" "Lewis" "Steele"])


(str (nth guys-whole-name 2) ", "
     (nth guys-whole-name 0) " "
     (nth guys-whole-name 1))

(let [[f-name m-name l-name] guys-whole-name]
  (str l-name ", " f-name " " m-name))

(def date-regex #"(\d{1,2})\/(\d{1,2})\/(\d{4})")
(let [rem (re-matcher date-regex "12/02/1975")]
  (when (.find rem)
    (let [[_ m d] rem]
      {:month m :day d})))

(let [[a b c & more] (range 10)]
  (println "a b c are: " a b c)
  (println "more are:" more))

(let [range-vec (vec (range 10))
      [a b c & more :as all] range-vec]
  (println "a b c are:" a b c)
  (println "more are:" more)
  (println "all are:" all))


(def guys-name-map
  {:f-name "Guy" :m-name "Lewis" :l-name "Steele"})

(let [{f-name :f-name m-name :m-name l-name :l-name} guys-name-map]
  (str l-name ", " f-name " " m-name))

(let [{:keys [f-name m-name l-name]} guys-whole-name]
  (str l-name ", " f-name " " m-name))

(let [{f-name :f-name, :as whole-name} guys-whole-name]
  (println "First name is " f-name)
  (println "Whole name is below:")
  whole-name)

(let [{:keys [title f-name m-name l-name] :or {title "Mr."}} guys-name-map]
  (println title f-name m-name l-name))

(defn whole-name [& args]
  (let [{:keys [f-name m-name l-name]} args]
    (str l-name ", " m-name " " l-name)))
(whole-name :f-name "Guy" :m-name "Lewis" :l-name "Steele")

(let [{first-thing 0, last-thing 3} [1 2 3 4]]
  [first-thing last-thing])

(defn print-last-name
  [{:keys [l-name]}]
  (println l-name))
(print-last-name guys-name-map)

(for [x (range 2) y (range 2)]
  [x y (bit-xor x y)])

(bit-xor 1 2)

(defn xors [max-x max-y]
  (for [x (range max-x)
        y (range max-y)]
    [x y (rem (bit-xor x y) 256)]))
(xors 2 2)

(def frame (java.awt.Frame.))


(for [method (seq (.getMethods java.awt.Frame))
      :let [method-name (.getName method)]
      :when (re-find #"Vis" method-name)]
  method-name)

;(.setVisible frame true)
;
;(.setSize frame (java.awt.Dimension. 200 200))
;
;(def gfx (.getGraphics frame))
;
;(.fillRect gfx 100 100 50 75)
;
;(.setColor gfx (java.awt.Color. 255 128 0))
;
;(.fillRect gfx 100 150 75 50)
;
;(doseq [[x y xor] (xors 500 500)]
;  (.setColor gfx (java.awt.Color. xor xor xor))
;  (.fillRect gfx x y 1 1))



