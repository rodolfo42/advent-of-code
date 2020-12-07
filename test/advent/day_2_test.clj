(ns advent.day-2-test
  (:require [clojure.test :refer :all]
            [advent.day-2 :refer [part-1 part-2]]))

(deftest part-1-test
  (is (= (part-1) 439)))

(deftest part-2-test
  (is (= (part-2) 584)))
