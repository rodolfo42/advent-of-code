(ns advent.day-7
  (:require [clojure.string :as str]))

;dim red bags contain 2 bright gold bags, 5 striped fuchsia bags.
;vibrant maroon bags contain no other bags.

(defn process-rule [s]
  (if (= "no other bags" s)
    nil
    (let [matches (re-matches #"([0-9]+) ([\w]+ [\w]+) bag(s*)" s)
          [qty color] (rest matches)]
      #{color})))

(defn process-rules [s]
  (->> (str/split s #",")
       (map str/trim)
       (map process-rule)
       (apply concat)))

(defn parse-line [s]
  (let [[color rule] (rest (re-matches #"([a-z]+ [a-z]+) bags contain ([0-9a-z ,]+)." s))]
    (hash-map color (process-rules rule))))

(defn expand-colors [rules color]
  (get rules color))

(defn read-input [input]
  (->> input
       line-seq
       (map parse-line)
       (apply merge)))

(defn flatten-colors [m]
  (->> m
       (map #(tree-seq m m (first %)))
       (map #(hash-map (first %) (set (rest %))))
       (apply merge)))

(defn find-possible-outermost-bags
  [rules search-color]
  (->> rules
       flatten-colors
       (filter #(some #{search-color} (val %)))
       (map first)))

(defn part-1 []
  (let [input (read-input #advent/input "day-7")]
    (count (find-possible-outermost-bags input "shiny gold"))))

(comment

  (part-1)
  ; 115
  )
