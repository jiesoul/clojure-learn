(ns com.jiesoul.joyofclojure.ch09)

(defrecord TreeNode [val l r])

(defn xconj [t v]
  (cond
    (nil? t) (TreeNode. v nil nil)
    (< v (:val t)) (TreeNode. (:val t) (xconj (:l t) v) (:r t))
    :else (TreeNode. (:val t) (:l t) (xconj (:r t) v))))

(defn xseq [t]
  (when t
    (concat (xseq (:l t)) [(:val t)] (xseq (:r t)))))

(def simple-tree (reduce xconj nil [3 5 2 4 6]))
(xseq simple-tree)

(defprotocol FIXO
  (fixo-push [fixo value])
  (fixo-pop [fixo])
  (fixo-peek [fixo]))

(extend-type TreeNode
  FIXO
  (fixo-push [node value]
    (xconj node value)))
(xseq (fixo-push simple-tree 5/2))

(extend-type clojure.lang.IPersistentVector
  FIXO
  (fixo-push [vector value]
    (conj vector value)))
(fixo-push [2 3 4 5 6] 5/2)

;(reduce fixo-push nil [3 5 2 4 6 0])

(extend-type nil
  FIXO
  (fixo-push [t v]
    (TreeNode. v nil nil)))
(xseq (reduce fixo-push nil [3 5 2 4 6 0]))

(extend-type TreeNode
  FIXO
  (fixo-push [node value]
    (xconj node value))
  (fixo-peek [node]
    (if (:l node)
      (recur (:l node))
      (:val node)))
  (fixo-pop [node]
    (if (:l node)
      (TreeNode. (:val node) (fixo-pop (:l node)) (:r node))
      (:r node))))

(extend-type clojure.lang.IPersistentVector
  FIXO
  (fixo-push [vector value]
    (conj vector value))
  (fixo-peek [vector]
    (peek vector))
  (fixo-pop [vector]
    (pop vector)))

(defn fixo-into [c1 c2]
  (reduce fixo-push c1 c2))

(xseq (fixo-into (TreeNode. 5 nil nil) [2 4 6 7]))

(seq (fixo-into [5] [2 4 6 7]))

(def tree-node-fixo
  {:fixo-push (fn [node value]
                (xconj node value))
   :fixo-peek (fn [node]
                (if (:l node)
                  (recur (:l node))
                  (:val node)))
   :fixo-pop (fn [node]
               (if (:l node)
                 (TreeNode. (:val node) (fixo-pop (:l node)) (:r node))
                 (:r node)))})

(extend TreeNode FIXO tree-node-fixo)

(xseq (fixo-into (TreeNode. 5 nil nil) [2 4 6 7]))

(defn fixed-fixo
  ([limit] (fixed-fixo limit []))
  ([limit vector]
    (reify FIXO
      (fixo-push [this value]
        (if (< (count vector) limit)
          (fixed-fixo limit (conj vector value))
          this))
      (fixo-peek [_]
        (peek vector))
      (fixo-pop [_]
        (pop vector)))))