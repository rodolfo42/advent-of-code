(ns advent.day-4
  (:require [clojure.string :as str]
            [utils :refer [split-in-batches-by between?]]))

(defn field-batch-str->id [field-batch-str]
  (apply hash-map (str/split field-batch-str #"( |:)")))

;byr (Birth Year) - four digits; at least 1920 and at most 2002.
;iyr (Issue Year) - four digits; at least 2010 and at most 2020.
;eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
;hgt (Height) - a number followed by either cm or in:
;If cm, the number must be at least 150 and at most 193.
;If in, the number must be at least 59 and at most 76.
;hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
;ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
;pid (Passport ID) - a nine-digit number, including leading zeroes.
;cid (Country ID) - ignored, missing or not.

(def validations
  {"byr" #(between? % 1920 2002)
   "iyr" #(between? % 2010 2020)
   "eyr" #(between? % 2020 2030)
   "hgt" #(let [unit    (apply str (take-last 2 %))
                measure (apply str (drop-last 2 %))]
            (case unit
              "cm" (between? measure 150 193)
              "in" (between? measure 59 76)
              false))
   "hcl" #(re-matches #"#[0-9a-f]{6}" %)
   "ecl" #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"}
   "pid" #(re-matches #"[0-9]{9}" %)})

(defn valid-id? [id]
  (let [required-keys ["byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"]]
    (every? id required-keys)))

(defn strict-valid-id? [id]
  (try
    (reduce-kv (fn [x k valid?]
                 (if (valid? (get id k))
                   x
                   (reduced false)))
               true
               validations)
    (catch Exception _
      false)))

(defn read-input [input]
  (->> input
       (split-in-batches-by str/blank?)
       (map (partial str/join " "))
       (map field-batch-str->id)))

(defn part-1 []
  (let [input (read-input #advent/input "day-4")]
    (->> input (filter valid-id?) count)))

(defn part-2 []
  (let [input (read-input #advent/input "day-4")]
    (->> input (filter strict-valid-id?) count)))

(comment

  (part-1)
  ; 235

  (part-2)
  ; 194
  )
