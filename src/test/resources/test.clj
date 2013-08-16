;道具模板
(ns me.vivia.game.test)

;定义变量
(def n 10)

;无参函数
(defn function[]
	(println "(inclojure) called function...")
	"success"
)

;有参函数
(defn hello[name]
	(str "hello," name "!")
)

;一维int数组
(println
    (int-array [1 2 3])
)

;二维int数组
(println (into-array (map int-array [[1 2] [3 4]])))
(println (into-array (map int-array [[1 2 3] [3 4 5]])))
