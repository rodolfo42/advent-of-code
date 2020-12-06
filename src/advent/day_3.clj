(ns advent.day-3
  (:require [clojure.java.io :as io]))

(defn read-input [file]
  (->>
    file
    io/resource
    io/reader
    line-seq
    (map cycle)))

(def tree \#)

(defn is-tree-at [tree-map pos]
  (->> tree-map first (drop pos) first (= tree)))

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

default-data-readers

(defn part-1 []
  (let [tree-map (read-input "day-3.input")]
    (count-trees tree-map 3 1)))

(defn part-2 []
  (let [tree-map    (read-input "day-3.input")
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
