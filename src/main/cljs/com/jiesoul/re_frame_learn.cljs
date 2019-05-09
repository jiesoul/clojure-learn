(ns com.jiesoul.re-frame-learn
  (:require [re-frame.core :as r]))

(defn delete-button
  [item-id]
  [:div.garbage-bin
   {:onclick #(r/dispatch [:delete-item item-id])}])

(defn h
  [coeffects event]
  (let [item-id (second event)
        db      (:db coeffects)]
    {:db (dissoc-in db [:item item-id])}))

(r/reg-event-fx
  :delete-item
  h)

(defn query-fn
  [db v]
  (:items db))

(r/reg-sub
  :query-items
  query-fn)

(defn item-render
  [])

(defn items-view
  []
  (let [items (r/subscribe [:query-items])]
    [:div (map item-render @items)]))

(r/dispatch [:buy 32343])

(def app-state (atom {}))

(defn buy-button
  [item-id]
  [:buton
   {:on-click (fn [e]
                (.preventDefault e)
                ajax/port (str "http://url.com/product" item-id "purchase")
                {:on-success #(swap! app-state asscoc :shoppin-cat $)
                 :on-error   #(swap! app-state update :errors conj)})}])