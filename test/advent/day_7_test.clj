(ns advent.day-7-test
  (:require [advent.day-7 :refer [parse-lines find-possible-outermost-bags part-1 part-2]]
            [clojure.test :refer :all]))

(def input ["light red bags contain 1 bright white bag, 2 muted yellow bags."
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags."
            "bright white bags contain 1 shiny gold bag."
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags."
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags."
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags."
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags."
            "faded blue bags contain no other bags."
            "dotted black bags contain no other bags."])

(deftest find-possible-outermost-bags-test
  (is (= (->> (parse-lines input)
              (find-possible-outermost-bags "shiny gold")
              count)
         4)))

(deftest part-1-test
  (is (= (part-1) 115)))

(deftest part-2-test
  (is (= (part-2) 1250)))
