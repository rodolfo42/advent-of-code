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

(defn tap [x]
  (println x)
  x)

(defn is-tree-at [tree-map pos]
  (->> tree-map first (drop pos) first (= tree)))

(defn count-trees [tree-map]
  (loop [map'       (drop 1 tree-map)
         slope-x    3
         tree-count 0]
    (if (seq map')
      (recur (next map')
             (+ slope-x 3)
             (if (is-tree-at map' slope-x)
               (inc tree-count)
               tree-count))
      tree-count)))

(defn part-1 []
  (let [tree-map (read-input "day-3.input")]
    (count-trees tree-map)))

(comment
  (part-1)
  ;272
  )
