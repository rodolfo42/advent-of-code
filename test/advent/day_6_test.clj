(ns advent.day-6-test
  (:require [clojure.test :refer :all]
            [advent.day-6 :refer [part-1 part-2]]))

(deftest part-1-test
  (is (= (part-1) 6297)))

(deftest part-2-test
  (is (= (part-2) 3158)))
