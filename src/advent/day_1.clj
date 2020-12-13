(ns advent.day-1
  (:require [utils :refer :all]))

(defn find-two-nums [target numbers]
  (some #(some-> (some #{(- target %)} numbers)
                 (vector %))
        numbers))

(defn find-three-nums [target numbers]
  (some #(some-> (find-two-nums (- target %) numbers)
                 (conj %))
        numbers))

(defn part-1 []
  (some->> (read-resource "day-1.input" read-string)
           (find-two-nums 2020)
           (apply *)))

(defn part-2 []
  (some->> (read-resource "day-1.input" read-string)
           (find-three-nums 2020)
           (apply *)))


(comment

  (part-1)
  ; 1013211

  (part-2)
  ; 13891280
  )
