(ns advent.day-4
  (:require [clojure.string :as str]))

(defn field-batch-str->id [field-batch-str]
  (apply hash-map (str/split field-batch-str #"( |:)")))

(defn group-id-fields [lines]
  (reduce (fn [all line]
            (if (seq line)
              (cons (str/trim (str (first all) " " line))
                    (next all))
              (cons "" all)))
          []
          lines))

(defn between?
  "True if n is between lower and upper (including), false otherwise"
  [n lower upper]
  (let [n' (cond-> n
             string? Integer/parseInt)]
    (and (>= n' lower)
         (<= n' upper))))

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

(defn valid-id? [id]
  (let [required-keys ["byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"]]
    (every? id required-keys)))

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
       line-seq
       group-id-fields
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
