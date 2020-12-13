(ns advent.day-8
  (:require [clojure.string :as str]
            [utils :refer :all]))

(defn parse-ops [s]
  (let [[op arg] (str/split s #" ")]
    {:ins (keyword op)
     :arg (read-string arg)}))

(defn execute
  [{:keys [cur] :as world} {:keys [ins arg]}]
  (let [world' (update world :past-pos conj cur)]
    (case ins
      :acc (-> world'
               (update :acc + arg)
               (update :cur inc))
      :jmp (update world' :cur + arg)
      :nop (update world' :cur inc))))

(defn detect-loop-entrypoint [ops]
  (loop [world {:past-pos #{} :cur 0 :acc 0}]
    (let [{:keys [past-pos cur] :as world'}
          (execute world (nth ops (:cur world)))]
      (if (past-pos cur)
        world'
        (recur world')))))

(defn change-op [op]
  (assoc op :ins (get {:nop :jmp
                       :jmp :nop
                       :acc :acc}
                      (:ins op))))

(defn loop? [{:keys [past-pos cur]}]
  (get past-pos cur))

(defn avoid-loops [ops]
  (loop [world {:past-pos #{} :cur 0 :acc 0 :changed? 0}]
    (if-let [next-op (nth ops (:cur world) nil)]
      (let [world' (execute world next-op)]
        (if (loop? world')
          (do
            (recur (execute (update world :changed? inc)
                            (change-op next-op))))
          (recur world')))
      (assoc world :finished true))))

(defn part-1 []
  (:acc (detect-loop-entrypoint (read-resource "day-8.input" parse-ops))))

(defn part-2 []
  (avoid-loops (read-resource "day-8.input" parse-ops)))

(comment
  (part-1))
; 1420

(try
  (part-2)
  (catch Exception ex
    (pr ex)))

