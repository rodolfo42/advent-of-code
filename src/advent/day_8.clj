(ns advent.day-8
  (:require [clojure.string :as str]))

(defn parse-ops [s]
  (let [[op arg] (str/split s #" ")]
    {:ins (keyword op)
     :arg (read-string arg)}))

(defn read-input [input]
  (->> input
       (map parse-ops)))

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
  (loop [{:keys [past-pos cur] :as world} {:past-pos #{} :cur 0 :acc 0}]
    (if (past-pos cur)
      world
      (recur (execute world (nth ops (:cur world)))))))

(defn part-1 []
  (let [input (read-input #advent/input "day-8")]
    (:acc (detect-loop-entrypoint input))))

(comment
  (part-1)
  ; 1420
  )
