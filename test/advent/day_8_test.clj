(ns advent.day-8-test
  (:require [advent.day-8 :refer [part-1]]
            [clojure.test :refer :all]))

(deftest part-1-test
  (is (= (part-1)
         1420)))
