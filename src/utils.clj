(ns utils
  (:require [puget.printer :as puget]))

(defn tap [x]
  `(let [x# ~x]
     (println '~x)
     (println "=>")
     (puget/cprint x#)
     x#))
