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

