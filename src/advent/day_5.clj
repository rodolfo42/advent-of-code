(ns advent.day-5
  (:require [utils :refer :all]))

(defn find-loc
  "Given a loc-str like 'FBBBFFB' or 'LRL', and a char to indicate a bit set like \\B or \\R,
  returns the position of the seat expressed by the string."
  [loc-str set-char]
  (let [leftmost-bit (dec (count loc-str))]
    (reduce-kv
      (fn [loc index c]
        (cond-> loc
          (= c set-char)
          (bit-set (- leftmost-bit index))))
      0
      (vec loc-str))))

(defn seat-loc [binary-str]
  (let [[rows columns] (split-at 7 binary-str)]
    {:row    (-> rows (find-loc \B))
     :column (-> columns (find-loc \R))}))

(defn seat-id [{:keys [row column]}]
  (-> row (* 8) (+ column)))

(defn find-missing [coll]
  (reduce (fn [last curr]
            (if-not (= last (dec curr))
              (reduced (dec curr))
              curr))
          (sort coll)))

(defn part-1 []
  (->> (read-resource "day-5.input" (comp seat-id seat-loc))
       (apply max)))

(defn part-2 []
  (->> (read-resource "day-5.input" (comp seat-id seat-loc))
       find-missing))

(comment

  (part-1)
  ; 922

  (part-2)
  ; 747
  )


