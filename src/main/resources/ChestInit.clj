;宝箱模板
(ns me.vivia.game.props)

;引入java类定义
(import '(me.vivia.game.props ChestT))

;生成所有的宝箱模板
(defn gen-all-chest-templates[templates]
	(map (fn[template] 
		(ChestT. 
		(template 0)	;编号
		(template 2)	;随机范围
		(into-array (map int-array (template 4)))	;候选库出产配置
		(template 3)	;是否出产多个物品
		)
	) templates)
)

;道具模板缓存
(def chest-templates-cache (gen-all-chest-templates chest-templates))

;根据模板编号查找宝箱
(defn find-chest-template[id]
	(first (filter #(= id (.getId %)) chest-templates-cache))
)