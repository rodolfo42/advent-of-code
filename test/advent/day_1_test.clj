(ns advent.day-1-test
  (:require [advent.day-1 :refer [part-1 part-2]]
            [clojure.test :refer :all]))

(deftest part-1-test
  (is (= (part-1) 1013211)))

(deftest part-2-test
  (is (= (part-2) 13891280)))
