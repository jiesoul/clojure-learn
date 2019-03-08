(ns com.jiesoul.reagent.ch01
  (:require [reagent.core :as r]))

(defn greet
  [name]
  [:div "Hello " name
   [:div {:style {:background "blue"}} "hello " "there"]])

(defn right-component
  [name]
  [:<>
   [:div "Hello"]
   [:div name]])

(defn timer-component []
  (let [seconds-elapsed (r/atom 0)]
    (fn []
      (js/setTimeout #(swap! seconds-elapsed inc) 1000)
      [:div "运行：" @seconds-elapsed "秒"])))

(defn my-component
  [x y z]
  (let []
    (r/create-class
      {:display-name "my-component"

       :component-did-mount
                     (fn [this]
                       (println "component-did-mount" this))

       :component-did-update
                     (fn [this old-argv]
                       (let [new-argv (rest (r/argv this))]
                         (println new-argv old-argv)))

       :reagent-render
                     (fn [x y z]
                       [:div (str x " " y " " z)])})))

(defn mouse-pos []
  (r/with-let [pointer (r/atom nil)
               handler #(swap! pointer assoc :x (.-pageX %)
                               :y (.-pageY %))]
              @pointer
              (finally
                (.removeEventListener js/document "mousemove" handler))))

(defn tracked-pos
  []
  [:div
   "Ponter move to: "]
  (str @(r/track mouse-pos)))
