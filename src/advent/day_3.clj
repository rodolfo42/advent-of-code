(ns advent.day-3
  (:require [utils :refer :all]))

(def tree \#)

(defn is-tree-at [tree-map pos]
  (-> tree-map first (nth pos) (= tree)))

(defn count-trees [tree-map slope-right slope-down]
  (loop [map'         (drop slope-down tree-map)
         tree-count   0
         slope-right' slope-right]
    (if (seq map')
      (recur (drop slope-down map')
             (if (is-tree-at map' slope-right')
               (inc tree-count)
               tree-count)
             (+ slope-right' slope-right))
      tree-count)))

(defn part-1 []
  (-> (read-resource "day-3.input" cycle)
      (count-trees 3 1)))

(defn part-2 []
  (let [tree-map    (read-resource "day-3.input" cycle)
        tree-counts (for [[right down] [[1 1]
                                        [3 1]
                                        [5 1]
                                        [7 1]
                                        [1 2]]]
                      (count-trees tree-map right down))]
    (apply * tree-counts)))

(comment
  (part-1)
  ; 272

  (part-2)
  ; 3898725600
  )
