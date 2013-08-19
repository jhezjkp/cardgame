package me.vivia.game.utils;

import java.util.Random;

/**
 * 随机数工具类，主要方面于测试
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public class RandomUtil {

	private static Random random = new Random();

	/**
	 * 设置随机数发生器
	 * 
	 * @param random
	 */
	public static void setRandom(Random random) {
		RandomUtil.random = random;
	}

	/**
	 * 判断是否随机成功
	 * 
	 * @param odds
	 *            目标机率
	 * @param totalOdss
	 *            总机率
	 * @return
	 */
	public static boolean isHit(int odds, int totalOdss) {
		return random.nextInt(totalOdss) < odds;
	}

	/**
	 * 获取指定范围内随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomInt(int min, int max) {
		// 传入的是等值则直接返回任意一个
		if (min == max) {
			return min;
		}
		// 保证传入参数顺序的正确性
		if (min > max) {
			int temp = min;
			min = max;
			max = temp;
		}
		return min + random.nextInt(max - min);
	}

	/**
	 * 获取0~指定范围内随机数
	 * 
	 * @param max
	 * @return
	 */
	public static int randomInt(int max) {
		return random.nextInt(max);
	}

}
