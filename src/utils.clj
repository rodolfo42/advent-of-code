(ns utils
  (:require [puget.printer :as puget]))

(defn tap [x]
  `(let [x# ~x]
     (println '~x)
     (println "=>")
     (puget/pprint x#)
     x#))
