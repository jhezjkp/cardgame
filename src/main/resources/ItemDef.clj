;道具效果定义
(ns me.vivia.game.props)

;引入java类定义
(import '(me.vivia.game.props ItemEffect))


;与java对象互操作
(defn 加体力[energy]
	(ItemEffect. (fn[target-obj] (.gainEnergy target-obj energy true)) )
)