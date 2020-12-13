(ns advent.day-1)

(defn read-numbers [input]
  (map #(Integer/parseInt %) input))

(defn find-two-nums [target numbers]
  (some #(some-> (some #{(- target %)} numbers)
                 (cons [%]))
        numbers))

(defn find-three-nums [target numbers]
  (some #(some-> (find-two-nums (- target %) numbers)
                 (conj %))
        numbers))

(defn part-1 []
  (some->> (read-numbers #advent/input "day-1")
           (find-two-nums 2020)
           (apply *)))

(defn part-2 []
  (some->> (read-numbers #advent/input "day-1")
           (find-three-nums 2020)
           (apply *)))


(comment

  (part-1)
  ; 1013211

  (part-2)
  ; 13891280
  )
