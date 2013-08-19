;宝箱模板
(ns me.vivia.game.props)

;配置说明
;1、宝箱需要有一个保底产出，即全服随机出产型至少有一个不限定出产总量的，当所有候选库都随机完后没有出产时，从不限定出产问题的候选物品库中抽取一个出产作为最终结果
;2、出产多个物品的宝箱每个候选物品库的满机率为100
(def chest-templates[
;		编号		备注                   随机范围类型   是否出产多个物品		[[候选库编号	出产总量(全局随机时)或保底出产数(玩家随机时) 出产机率]	 ...]
	[   1       "全服大杂汇宝箱多个出产"	  全服随机	       true         [[1	0	70]	[2 100 30]]];保底出产物品库1，物品库2每天只出产100份
	[   2       "全服大杂汇宝箱单个出产"	  全服随机	       false        [[1	0	70]	[2 100 30]]];保底出产物品库1，物品库2每天只出产100份
	[   3       "玩家大杂汇宝箱多个出产"	  玩家随机	       true         [[1	100	85]	[2 150 15]]]
	[   4       "玩家大杂汇宝箱单个出产"	  玩家随机	       false        [[1	100	85]	[2 150 15]]]
])