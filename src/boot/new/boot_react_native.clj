(ns boot.new.boot-react-native
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [clojure.java.shell :as shell]
            [cpath-clj.core :as cp]
            [camel-snake-kebab.core :as kebab]
            [boot.new.templates :refer [renderer name-to-path ->files]]))

(defn get-rel-path [path from]
  (last (str/split path from)))

(defn replace-excl [string]
  (str/replace string #"!" ""))

(defn list-files [path]
  (->>
    (cp/resources path)
    (map #(first (val %)))))

(defn sandr [path data]
  (str/replace path #"mattsum\/simple_example|mattsum.simple_example|SimpleExampleApp|mattsum.simple-example"
    {"mattsum/simple_example" (:sanitized data)
     "mattsum.simple_example" (:sanitized data)
     "mattsum.simple-example" (:name data)
     "SimpleExampleApp" (:capitalized data)}))

(defn copy-files [data path]
  (apply (partial ->files data)
    (->> (list-files path)
      (mapv #(vector (sandr (get-rel-path (.toString %) #"boot/new/boot_react_native/example/") data)
              (sandr (slurp %) data))))))

(defn boot-react-native
  "Main function. Moves and search/replaces files from resources."
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)
              :capitalized (kebab/->PascalCase name)}]

    (println "Generating fresh boot-react-native project.")
    (println "Copying files")
    (copy-files data "boot/new/boot_react_native/")

    ;; TODO: stop "jar:file:" from being generated.
    (shell/with-sh-dir name (shell/sh "rm" "-rf" "jar:file:"))

    (println "Running `npm install`")
    (if-let [error (:err (shell/with-sh-dir (str name "/app") (shell/sh "npm" "install")))]
      (println "Error:" error)
      (println "Project generated succesfully. `cd " name "` and `boot dev --platform ios` to start dev"))))

