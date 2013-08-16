;道具效果定义等
(ns me.vivia.game.props)

;引入java类定义
(import '(me.vivia.game.common Quality ItemUseTarget ChestRandomScope))

;品质定义
(def 白 Quality/White)
(def 绿 Quality/Green)
(def 蓝 Quality/Blue)
(def 紫 Quality/Purple)

;道具使用目标定义
(def 无目标 ItemUseTarget/None)
(def 玩家 ItemUseTarget/Player)
(def 卡牌 ItemUseTarget/Card)
(def 背包 ItemUseTarget/Pack)

;宝箱随机范围
(def 全服随机 ChestRandomScope/GlobalScope)
(def 玩家随机 ChestRandomScope/PlayerScope)

;判断指定模板编号的物品是否是道具
(defn is-item-template[template-id]
	;道具模板在1-499之间
	(and (>= template-id 1) (<= template-id 499))
)

