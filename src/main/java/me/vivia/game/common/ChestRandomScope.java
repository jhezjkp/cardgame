package me.vivia.game.common;

/**
 * 宝箱随机范围
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public enum ChestRandomScope {

	/** 全局(服)随机(限制稀有物品出产总量) */
	GlobalScope,
	/** 玩家随机(每N个至少得一个稀有物品) */
	PlayerScope,

}
