(ns boot.new.boot-react-native
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [camel-snake-kebab.core :as kebab]
            [boot.new.templates :refer [renderer name-to-path ->files]]))

(def render (renderer "boot-react-native"))

(defn get-rel-path [path from]
  (str/replace-first path from ""))

(defn list-files [path]
  (->> (file-seq (io/file path))
    (filter #(.isFile %))
    (map #(.getPath %))
    (map #(get-rel-path % #"resources/boot/new/boot_react_native/"))))

(defn sandr [path data]
  (str/replace path #"mattsum\/simple_example|mattsum.simple_example|SimpleExampleApp|mattsum.simple-example"
    {"mattsum/simple_example" (:sanitized data)
     "mattsum.simple_example" (:sanitized data)
     "mattsum.simple-example" (:name data)
     "SimpleExampleApp" (:capitalized data)}))

(defn boot-react-native
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)
              :capitalized (kebab/->PascalCase name)}
        example-folder "resources/boot/new/boot_react_native/example/"]

    (println "Generating fresh 'boot new' boot-react-native project.")
    (apply (partial ->files data)
      (->> (list-files example-folder)
        (mapv #(vector (sandr (get-rel-path % #"example/") data)
                       (sandr (render % data) data)))))))
