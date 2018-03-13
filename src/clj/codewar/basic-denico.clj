  ;Task
  ;Write a function deNico/de_nico() that accepts two parameters:
  ;
  ;key/$key - string consists of unique letters and digits
  ;message/$message - string with encoded message
  ;and decodes the message using the key.
  ;
  ;First create a numeric key basing on the provided key by assigning each letter position in which it is located after setting the letters from key in an alphabetical order.
  ;
  ;For example, for the key crazy we will get 23154 because of acryz (sorted letters from the key).
  ;Let's decode cseerntiofarmit on using our crazy key.
  ;
  ;1 2 3 4 5
  ;---------
  ;c s e e r
  ;n t i o f
  ;a r m i t
  ;o n
  ;After using the key:
  ;
  ;2 3 1 5 4
  ;---------
  ;s e c r e
  ;t i n f o
  ;r m a t i
  ;o n
  ;Notes
  ;The message is never shorter than the key.
  ;Don't forget to remove trailing whitespace after decoding the message
  ;Examples
  ;deNico("crazy", "cseerntiofarmit on  ") => "secretinformation"
  ;deNico("abc", "abcd") => "abcd"
  ;deNico("ba", "2143658709") => "1234567890"
  ;deNico("key", "eky") => "key"
  ;Check the test cases for more examples.
  ;
  ;Related Kata
  ;Basic Nico - encode

(defn denico [k m]
  (let [c (count k)
        km (zipmap (sort (seq k)) (iterate inc 1) )
        kk (map #(get km %) (seq k))
        fm (map #(zipmap (iterate inc 1) %) (partition-all c m))
        zm (map (comp #(reduce str %) (fn [m] (reduce #(conj %1 (m %2)) [] kk))) fm)]
    (clojure.string/trim (apply str zm))))

(denico "crazy" "cseerntiofarmit on  ")

(def m "am43p5hl9ykome47p1k9qr49i6jnmhh69p0zvslrnl8eafxuq2q7jc0k8uc4cbw186p4gv0kxwgfghgi3f2o1lzcp33nztchayjlex6pcdsw7g8295r8edjdsg3145      oy41u eg5 yn6nh")
(def k "pgrsonyivfwljbdc7qm21")
(partition-all (count k) m)
(denico k m)
(sort (seq k))
(zipmap (sort (seq k)) (iterate inc 1))
(map #(get km %) (seq k))
(map #(get (zipmap (sort (seq k)) (iterate inc 1)) %) (seq k))

(map #(zipmap (iterate inc 1) %) (partition-all (count k) m))

Message:'am43p5hl9ykome47p1k9qr49i6jnmhh69p0zvslrnl8eafxuq2q7jc0k8uc4cbw186p4gv0kxwgfghgi3f2o1lzcp33nztchayjlex6pcdsw7g8295r8edjdsg3145      oy41u eg5 yn6nh',
Key'pgrsonyivfwljbdc7qm21',
Expected:'4lp1emq9kh9ky35p47omazmsl0plhrnn6hij69v94r82c4k0wqcqbj7fuxauce8h0i3gfokfv2wxpg46gg81jnexyacz63pctc3pzlhl1d9g3jd551248r78gwsesd5yyngeh46onu1'

 (not ("4lp1emq9kh9ky35p47omazmsl0plhrnn6hij69v94r82c4k0wqcqbj7fuxauce8h0i3gfokfv2wxpg46gg81jnexyacz63pctc3pzlhl1d9g3jd551248r78gwsesd5yyngeh46onu1"
        "h9a34qm5p4omkk9pe7y1lnnrizlpjs994r6h60vhlmqb8f8w0ucacecjqxku742v21phofgi6g8fwk4ggx303p1cjca3ezhl6czpyltxn24d7d5d8gwes185gjsr39on  5he y   6u4 g 1ny"))