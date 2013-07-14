package me.vivia.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.vivia.game.common.Const;
import me.vivia.game.props.IProps;
import me.vivia.game.props.Item;

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

	/** 体力值 */
	private int energy = Const.MAX_ENERGY;
	/** 拥有的物品(道具和装备) */
	private Map<Long, IProps> propsMap = new HashMap<Long, IProps>();
	/** 拥有的道具 */
	private List<Item> items = new ArrayList<Item>();

	public Player() {
	}

	public Player(int energy, Map<Long, IProps> propsMap) {
		super();
		this.energy = energy;
		this.propsMap = propsMap;
		for (IProps props : propsMap.values()) {
			if (props instanceof Item) {
				items.add((Item) props);
			}
		}
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

	/**
	 * 堆叠道具
	 * 
	 * @param item
	 * @return 如果物品整体堆叠到其他物品上了则返回堆叠的物品的实例编号，否则返回原物品实例编号
	 */
	private long stackItems(Item item) {
		long id = 0;
		for (Item existItem : items) {
			if (existItem.getTemplateId() != item.getTemplateId()) {
				continue; // 不同道具
			}
			int avaliableStackQuantity = item.getMaxStackQuantity()
					- existItem.getQuantity();
			if (avaliableStackQuantity == 0) {
				continue; // 无法再堆叠了
			}
			id = existItem.getId();
			if (avaliableStackQuantity >= item.getQuantity()) { // 能堆叠完剩下的
				existItem.addQuantity(item.getQuantity());
				item.deductQuantity(item.getQuantity());
			} else if (avaliableStackQuantity > 0) { // 只能堆叠一部分，即当前的现有物品的可用堆叠数
				existItem.addQuantity(avaliableStackQuantity);
				item.deductQuantity(avaliableStackQuantity);
			}
			// 通知客户端数量变更
			logger.info("client notify:\titem changed\t" + existItem.getName()
					+ "x" + existItem.getQuantity());
			if (item.getQuantity() == 0) {
				break; // 堆叠完了则退出循环
			}
		}
		if (item.getQuantity() > 0) {
			items.add(item);
			// 通知客户端新增加了一个道具
			logger.info("client notify:\titem added\t" + item.getName() + "x"
					+ item.getQuantity());
			return item.getId();
		} else {
			return id;
		}
	}

	@Override
	public long addProps(IProps newProps) {
		if (newProps.getQuantity() <= 0) {
			throw new IllegalStateException("物品数量为0或为负！");
		}
		if (newProps instanceof Item) { // 道具
			long id = stackItems((Item) newProps);
			if (id == newProps.getId()) { // 道具没有与现有道具堆叠
				propsMap.put(id, newProps);
			} else {

			}
			return id;
		}
		return newProps.getId();
	}

}
