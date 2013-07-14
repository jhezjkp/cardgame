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
 * 背包控制器
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public class PackController {

	private Logger logger = LoggerFactory.getLogger(PackController.class);

	/** 玩家 */
	private IPlayer player;
	/** 拥有的物品(道具和装备) */
	private Map<Long, IProps> propsMap = new HashMap<Long, IProps>();
	/** 拥有的道具 */
	private List<Item> items = new ArrayList<Item>();

	public PackController(IPlayer player, Map<Long, IProps> propsMap) {
		super();
		this.player = player;
		this.propsMap = propsMap;
		this.propsMap = propsMap;
		for (IProps props : propsMap.values()) {
			if (props instanceof Item) {
				items.add((Item) props);
			}
		}
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

	/**
	 * 给玩家增加物品
	 * 
	 * @param props
	 * @return 如果物品整体堆叠到其他物品上了则返回堆叠的物品的实例编号，否则返回原物品实例编号
	 */
	public long addProps(IProps newProps) {
		if (newProps.getQuantity() <= 0) {
			throw new IllegalStateException("物品数量为0或为负！");
		}
		// 对银两道具进行特殊处理
		if (newProps.getTemplateId() == Const.TEMPLATE_ID_MONEY) {
			player.gainMoney(newProps.getQuantity());
			return -1;
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

	/**
	 * 根据实例编号查找道具
	 * 
	 * @param itemId
	 * @return
	 */
	public Item findItem(long itemId) {
		IProps props = propsMap.get(itemId);
		if (props != null && props instanceof Item) {
			return (Item) props;
		}
		return null;
	}

	/**
	 * 生成指定的物品列表
	 * 
	 * @param array
	 *            指定物品的模板编号及数量
	 */
	public void genSpecifiedProps(int[][] array) {
		logger.info("=====");
		for (int[] config : array) {
			System.out.println(config[0] + "\t" + config[1]);
		}
	}

}
