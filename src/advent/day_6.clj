(ns advent.day-6
  (:require [clojure.string :as str]
            [utils :refer [split-in-batches-by]]))

(defn read-input [input]
  (->> input
       line-seq
       (split-in-batches-by str/blank?)))

(defn find-yes-answers [people-answers]
  (set (apply concat people-answers)))

(defn part-1 []
  (->> (read-input #advent/input "day-6")
       (map (comp count find-yes-answers))
       (apply +)))

(comment

  (part-1)
  ; 6297
  )
