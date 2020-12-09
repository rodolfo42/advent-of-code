(ns advent.day-8-test
  (:require [clojure.test :refer :all]
            [advent.day-8 :refer [part-1]]))

(deftest part-1-test
  (is (= (part-1)
         1420)))
