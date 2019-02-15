(ns com.jiesoul.joyofclojure.udp
  (:refer-clojure :exclude [get]))

(defn beget
  [this proto]
  (assoc this ::prototype proto))

(beget {:sub 0} {:super 1})

(defn get [m k]
  (when m
    (if-let [[_ v] (find m k)]
      v
      (recur (::prototype m) k))))

(get (beget {:sub 0} {:super 1}) :super)

(def put assoc)

(def cat {:likes-dogs true, :ocd-bathing true})
(def morris (beget {:likes-9lives true} cat))
(def post-traumatic-morris (beget {:likes-dogs nil} morris))

(get cat :likes-dogs)
(get morris :likes-dogs)
(get post-traumatic-morris :likes-dogs)
(get post-traumatic-morris :likes-9lives)

(defmulti compiler :os)
(defmethod compiler ::unix [m] (get m :c-compiler))
(defmethod compiler ::osx [m] (get m :llvm-compiler))

(def clone (partial beget {}))
(def unix {:os ::unix, :c-compiler "cc" :home "/home" :dev "/dev"})
(def osx (-> (clone unix)
             (put :os ::osx)
             (put :llvm-compiler "clang")
             (put :home "/Users")))

(compiler unix)

(defmulti home :os)
(defmethod home ::unix [m] (get m :home))

(home unix)
(derive ::osx ::unix)
(home osx)
(parents ::osx)
(ancestors ::osx)
(descendants ::unix)
(isa? ::osx ::unix)
(isa? ::unix osx)

(derive ::osx ::bsd)
(defmethod home ::bsd [m] "/home")
(prefer-method home ::unix ::bsd)
(home osx)

(defmulti compile-cmd (juxt :os compiler))
(defmethod compile-cmd [::osx "gcc"] [m]
  (str "/usr/bin" (get m :c-compiler)))
(defmethod compile-cmd :default [m]
  (str "Unsure where to locate " (get m :c-compiler)))

(compile-cmd osx)
(compile-cmd unix)