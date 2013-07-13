package me.vivia.game;

import me.vivia.game.common.Const;

/**
 * 玩家实现类，由于本项目是一个原型设计项目故省略db层
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public class Player implements IPlayer {

	/** 体力值 */
	private int energy = Const.MAX_ENERGY;

	@Override
	public int getEnergy() {
		return energy;
	}

	@Override
	public int gainEnergy(int value, boolean allowExceed) {
		energy += Math.abs(value);
		if (energy > Const.MAX_ENERGY && !allowExceed) {
			energy = Const.MAX_ENERGY;
		}
		return energy;
	}

	@Override
	public int deductEnergy(int value) throws IllegalStateException {
		if (energy < value) {
			throw new IllegalStateException("体力不足！");
		}
		energy -= value;
		return energy;
	}

}
