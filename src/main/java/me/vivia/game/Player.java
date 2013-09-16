package me.vivia.game;

import java.util.HashMap;
import java.util.Map;

import me.vivia.game.common.Const;
import me.vivia.game.props.IProps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 玩家实现类，由于本项目是一个原型设计项目故省略db层
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public class Player implements IPlayer {

	private Logger logger = LoggerFactory.getLogger(Player.class);

	/** 体力 */
	private int energy = Const.MAX_ENERGY;
	/** 银两 */
	private long money;
	/** 背包控制器 */
	private PackController packController;
	/** 玩家随机宝箱开箱记录 key-宝箱编号 value-[0]已开的宝箱数 [1]稀有出产数 */
	private Map<Integer, int[]> chestRecordMap;

	public Player() {
		this(Const.MAX_ENERGY, new HashMap<Long, IProps>(),
				new HashMap<Integer, int[]>());
	}

	public Player(int energy, Map<Long, IProps> propsMap,
			Map<Integer, int[]> chestRecordMap) {
		super();
		this.energy = energy;
		this.packController = new PackController(this, propsMap);
		this.chestRecordMap = chestRecordMap;
	}

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
		// 通知客户端体力变更
		logger.info("client notify:\tenergy " + energy);
		return energy;
	}

	@Override
	public int deductEnergy(int value) throws IllegalStateException {
		if (energy < value) {
			throw new IllegalStateException("体力不足！");
		}
		energy -= value;
		// 通知客户端体力变更
		logger.info("client notify:\tenergy " + energy);
		return energy;
	}

	@Override
	public PackController getPackController() {
		return packController;
	}

	@Override
	public long getMoney() {
		return money;
	}

	@Override
	public long gainMoney(long value) {
		money += value;
		// 通知客户端银两变更
		logger.info("client notify:\tmoney changed\t" + money);
		return money;
	}

	@Override
	public long deductMoney(long value) throws IllegalStateException {
		if (money < value) {
			throw new IllegalStateException("银两不足！");
		}
		money -= value;
		// 通知客户端银两变更
		logger.info("client notify:\tmoney changed\t" + money);
		return money;
	}

}
