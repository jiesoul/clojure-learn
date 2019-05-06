(ns com.jiesoul.ant.weixin)

(def url (atom "https://chuansongme.com/account/chinaetfs/recent"))

(println (slurp @url))
