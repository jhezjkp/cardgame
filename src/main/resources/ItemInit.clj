;道具效果定义
(ns me.vivia.game.props)

;引入java类定义
(import '(me.vivia.game.props ItemT ItemEffect CandidateT CandidateOption))

;生成所有的道具模板
(defn gen-all-item-templates[templates]
	(map (fn[template] 
		(ItemT. 
		(template 0)	;编号
		(template 1)	;名称
		(template 2)	;描述
		(template 3)	;品质
		(template 4)	;最大堆叠数
		(template 5)	;作用目标
		(template 6)	;使用效果
		(template 7)	;图标编号
		)
	) templates)
)

;道具模板缓存
(def item-templates-cache (gen-all-item-templates item-templates))

;根据模板编号查找道具
(defn find-item-template[id]
	(first (filter #(= id (.getId %)) item-templates-cache))
)

;生成所有的物品候选库
(defn gen-all-candidate-templates[templates]
	(map (fn[template] 
		(CandidateT. 
		(template 0)	;编号
		(into-array CandidateOption (map (fn[option] (CandidateOption.
				(option 0)	;模板编号
				(option 1)	;数量
				(option 2)	;出产机率
				))
			(template 2)))	;出产项
		)
	) templates)
)

;物品候选库缓存
(def candicate-templates-cache (gen-all-candidate-templates candidate-templates))

;根据模板编号查找物品候选库
(defn find-candidate-template[id]
	(first (filter #(= id (.getId %)) candicate-templates-cache))
)