(ns advent.day-4-test
  (:require [advent.day-4 :refer [read-input strict-valid-id? part-1 part-2]]
            [clojure.test :refer :all]
            [utils :refer :all]))

(deftest strict-valid-id?-test
  (testing "invalid passports"
    (let [input (read-input (read-resource "day-4/invalid-passports.input"))]
      (is (= 0 (->> input (filter strict-valid-id?) count)))))

  (testing "valid passports"
    (let [input (read-input (read-resource "day-4/valid-passports.input"))]
      (is (= 4 (->> input (filter strict-valid-id?) count))))))

(deftest part-1-test
  (is (= (part-1) 235)))

(deftest part-2-test
  (is (= (part-2) 194)))
