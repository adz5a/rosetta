(ns rosetta.core
  (:require ))

(enable-console-print!)


;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(def truthy? 
  (fn [a]
    (if a
      true
      false)))

(def booleanCast
  (fn [a]
    (if (= 1 a)
      true
      false)))

(def binaryCast
  (fn [a]
    (if a
      1
      0)))

(def binarySeq
  (fn [number]
    (loop [bseq [(mod number 2)]
           number (quot number 2)]
      (if (< number 2)
        (conj bseq number)
        (recur 
          (conj bseq (mod number 2))
          (quot number 2))))))




(def xor
  (fn [a b]
    ;; will cast its args to true / false based on their thruthiness
    (not (= (truthy? a) (truthy? b)))))

(def computeBinary
  (fn [binarySeq]
    (loop [pow2 1
           result 0
           binarySeq binarySeq]
      (if (empty? binarySeq)
        result
        (recur
          (* 2 pow2)
          (+ result (* pow2 (first binarySeq)))
          (rest binarySeq))))))

()

(def xorSeq
  (fn [seq1 seq2]
    (loop [seq1 seq1
           seq2 seq2
           res []]
      (let [end1? (empty? seq1)
            end2? (empty? seq2)]
        (if (and end1? end2?)
          res
          (let [v1 (booleanCast (first seq1))
                v2 (booleanCast (first seq2))]
            (recur 
              (rest seq1)
              (rest seq2)
              (conj res (xor v1 v2)))))))))

(def values (for [x (range 0 256)
                  y (range 0 256)
                  :let [pos [x y]]]
              {:pos pos
               :value (computeBinary (map binaryCast (xorSeq 
                                                       (binarySeq x)
                                                       (binarySeq y))))}))

(def printPixel
  (fn [pixel]
    (do
      (.drawRect js/draw #js {:position (:pos pixel)
                              :color (repeat 3 (:value pixel))
                              :size [1 1]}))))

(loop [pixels values]
  (if (empty? pixels)
    nil
    (do
      (printPixel (first pixels))
      (recur (rest pixels)))))
(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)

  

  )
