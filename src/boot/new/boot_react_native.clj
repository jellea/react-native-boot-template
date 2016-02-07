(ns boot.new.boot-react-native
  (:require [boot.new.templates :refer [renderer name-to-path ->files]]))

(def render (renderer "boot-react-native"))

(defn boot-react-native
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (println "Generating fresh 'boot new' boot-react-native project.")
    (->files data
             ["src/{{sanitized}}/foo.clj" (render "foo.clj" data)])))
