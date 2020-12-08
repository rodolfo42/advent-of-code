(ns advent.day-6
  (:require [clojure.string :as str]
            [utils :refer [split-in-batches-by]]))

(defn read-input [input]
  (->> input
       (split-in-batches-by str/blank?)))

(defn find-any-yes-answers [people-answers]
  (set (apply concat people-answers)))

(defn all-yes? [question answers]
  (every? #(some #{question} %) answers))

(defn find-all-yes-answers [group-answers]
  (let [answers   (map seq group-answers)
        questions (map char (range (int \a) (inc (int \z))))]
    (filter #(all-yes? % answers) questions)))

(defn part-1 []
  (->> (read-input #advent/input "day-6")
       (map (comp count find-any-yes-answers))
       (apply +)))

(defn part-2 []
  (->> (read-input #advent/input "day-6")
       (map (comp count find-all-yes-answers))
       (apply +)))

(comment

  (part-1)
  ; 6297

  (part-2)
  ; 3158
  )
