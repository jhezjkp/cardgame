;道具效果定义
(ns me.vivia.game.props)

;引入java类定义
(import '(me.vivia.game.common Quality ItemUseTarget))

;品质定义
(def 白 Quality/White)
(def 绿 Quality/Green)
(def 蓝 Quality/Blue)
(def 紫 Quality/Purple)

;道具使用目标定义
(def 无目标 ItemUseTarget/None)
(def 玩家 ItemUseTarget/Player)
(def 卡牌 ItemUseTarget/Card)