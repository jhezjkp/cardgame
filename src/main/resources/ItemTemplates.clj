;道具模板
(ns me.vivia.game.props)

(def item-templates[
;		编号		名称			描述									品质		最大堆叠数	作用对象		使用效果										图标编号
	[   1       "茅台"   	"国酒茅台，饮之可恢复40点体力"			蓝		99			玩家			(加体力 40)									1		]
	[   2       "培养丹"   	"将潜力转化为卡牌基本属性的神奇丹药"		紫		99			无目标		(消耗品)										2		]
	[   99      "银两"   	"白花花的银两"							蓝		1			玩家			(消耗品)										99		]
	[   101     "新手礼包"   	"内有10000银两，5瓶茅台，祝您游戏愉快"	蓝		1			背包			(生成固定物品 [[99 10000] [1 5]])				101		]
	[   151     "神秘礼包"   	"天知道里面有什么，祝您游戏好运"			蓝		99			背包			(生成随机物品 [[99 10000] [1 5]])				101		]
])