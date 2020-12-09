(ns advent.day-7
  (:require [clojure.string :as str]))

;dim red bags contain 2 bright gold bags, 5 striped fuchsia bags.
;vibrant maroon bags contain no other bags.

(defn process-rule [s]
  (if (= "no other bags" s)
    nil
    (let [matches (re-matches #"([0-9]+) ([\w]+ [\w]+) bag(s*)" s)
          [qty color] (rest matches)]
      #{[color (Integer/parseInt qty)]})))

(defn process-rules [s]
  (->> (str/split s #",")
       (map str/trim)
       (map process-rule)
       (apply concat)))

(defn parse-line [s]
  (let [[color rule] (rest (re-matches #"([a-z]+ [a-z]+) bags contain ([0-9a-z ,]+)." s))]
    (hash-map color (process-rules rule))))

(defn read-input [input]
  (->> input
       (map parse-line)
       (apply merge)))

(defn flatten-colors [m]
  (->> m
       (map (fn [[k v]] [k (set (map first v))]))
       (into {})))

(defn find-possible-outermost-bags
  [rules search-color]
  (let [flattened (flatten-colors rules)]
    (->> flattened
         keys
         (map #(->> % (tree-seq flattened flattened) rest))
         (filter #(some #{search-color} %)))))

(defn find-bag-contents
  [rules color]
  (tree-seq #(-> % first rules)
            (fn [[c q]]
              (->> (get rules c)
                   (map (fn [[k v]] [k (* v q)]))))
            [color 1]))

(defn part-1 []
  (let [input (read-input #advent/input "day-7")]
    (count (find-possible-outermost-bags input "shiny gold"))))

(defn part-2 []
  (let [input (read-input #advent/input "day-7")]
    (->> "shiny gold"
         (find-bag-contents input)
         (map second)
         (apply +)
         dec)))

(comment

  (part-1)
  ; 115

  (part-2)
  ; 1250
  )

