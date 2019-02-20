(ns com.jiesoul.codewar.vowel-indices)

(defn vowel-indices [word]
  (let [vw #{\a \e \i \o \u}]
    (reduce-kv
       (fn [m k v] (contains? vw v)
            (conj m (inc k)))
      []
       (seq word))))
