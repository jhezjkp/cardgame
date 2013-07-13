package me.vivia.game;

/**
 * 玩家接口
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public interface IPlayer {

	/**
	 * 获得当前的体力值
	 * 
	 * @return
	 */
	int getEnergy();

	/**
	 * 获得体力
	 * 
	 * @param value
	 *            获得的体力点数
	 * @param allowExceed
	 *            是否允许超过上限
	 * @return 最新的体力值
	 */
	int gainEnery(int value, boolean allowExceed);

	/**
	 * 扣除体力点
	 * 
	 * @param value
	 *            将要扣除的数值
	 * @return 最新的体力值
	 * @throws IllegalStateException
	 *             如果扣除指定数值后最终的体力值为负将抛出该异常
	 */
	int deductEnergy(int value) throws IllegalStateException;

}
