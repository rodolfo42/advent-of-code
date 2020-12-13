(ns advent.day-7
  (:require [clojure.string :as str]
            [utils :refer :all]))

;dim red bags contain 2 bright gold bags, 5 striped fuchsia bags.
;vibrant maroon bags contain no other bags.

(defn decode-bag-content [bag-content]
  (if-let [[_ qty color] (re-matches #"([0-9]+) ([\w]+ [\w]+) bag(s*)" bag-content)]
    [color (read-string qty)]))

(defn decode-contents [bags]
  (->> (str/split bags #",( *)")
       (map decode-bag-content)
       (into {})))

(defn parse-line [line]
  (let [[_ color bags] (re-matches #"([a-z]+ [a-z]+) bags contain ([0-9a-z ,]+)." line)]
    [color (decode-contents bags)]))

(defn flatten-colors [m]
  (zipmap (keys m)
          (->> m vals (map (comp set keys)))))

(defn find-possible-outermost-bags
  [search-color rules]
  (let [flattened (flatten-colors rules)]
    (->> flattened
         keys
         (map #(->> % (tree-seq flattened flattened) rest))
         (filter #(some #{search-color} %)))))

(defn find-bag-contents
  [color rules]
  (tree-seq #(-> % first rules)
            (fn [[c q]]
              (->> (get rules c)
                   (map (fn [[k v]] [k (* v q)]))))
            [color 1]))

(defn parse-lines [input]
  (->> input
       (map parse-line)
       (into {})))

(defn part-1 []
  (->> (read-resource "day-7.input")
       parse-lines
       (find-possible-outermost-bags "shiny gold")
       count))

(defn part-2 []
  (->> (read-resource "day-7.input")
       parse-lines
       (find-bag-contents "shiny gold")
       (map second)
       (apply +)
       dec))

(comment

  (part-1)
  ; 115

  (part-2)
  ; 1250
  )

