(ns advent.day-5-test
  (:require [advent.day-5 :refer [find-missing part-1 part-2]]
            [clojure.test :refer :all]))

(deftest find-missing-test
  (is (= (find-missing [450 453 454 451 455 456 449])
         452)))

(deftest part-1-test
  (is (= (part-1) 922)))

(deftest part-2-test
  (is (= (part-2) 747)))
