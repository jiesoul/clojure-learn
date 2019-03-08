(ns com.jiesoul.core
  (:require [reagent.core :as r]
            [com.jiesoul.reagent.ch01 :refer [greet right-component timer-component
                                              my-component tracked-pos]]))

(defn home-page []
  [:div
   [:h1 "welcome cljs world"]
   [tracked-pos]
   [my-component 1 2 3]
   [timer-component]
   [greet "SOUL"]
   [right-component "武家坡"]
   [:p "龙潭虎穴孤去闯"]
   [:div {:class "parent"}
    [:p {:id "child-one"} "I'm first child element."]
    [:p "I'm the second child element."]
    ]
   ])

;; -------
;; 初始化 app
(defn mount-root []
  (r/render
    home-page
    (.getElementById js/document "app")))

(defn init! []
  (mount-root))