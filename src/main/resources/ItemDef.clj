;道具效果定义
(ns me.vivia.game.props)

;引入java类定义
(import '(me.vivia.game.props ItemEffect))

;以下为道具使用效果
(defn 消耗品[]
	(ItemEffect. (fn[]) )
)

(defn 加体力[energy]
	(ItemEffect. (fn[target-obj] (.gainEnergy target-obj energy true)) )
)

(defn 生成固定物品[template-id-list]
	(ItemEffect. (fn[target-obj] (.genSpecifiedProps target-obj 
		(map #(int-array %) template-id-list) 
		) ) )
)