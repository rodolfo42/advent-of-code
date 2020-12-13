(ns advent.day-3-test
  (:require [advent.day-3 :refer [part-1 part-2]]
            [clojure.test :refer :all]))

(deftest part-1-test
  (is (= (part-1) 272)))

(deftest part-2-test
  (is (= (part-2) 3898725600)))
