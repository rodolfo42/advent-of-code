(ns advent.day-4-test
  (:require [clojure.test :refer :all]
            [advent.day-4 :as day-4]))

(deftest strict-valid-id?-test
  (testing "invalid passports"
    (let [input (day-4/read-input "day-4/invalid-passports.input")]
      (is (= 0 (->> input (filter day-4/strict-valid-id?) count)))))

  (testing "valid passports"
    (let [input (day-4/read-input "day-4/valid-passports.input")]
      (is (= 4 (->> input (filter day-4/strict-valid-id?) count))))))
