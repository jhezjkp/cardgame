package me.vivia.game.props;

import me.vivia.game.utils.ScriptUtil;

/**
 * 道具实例对象
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public class Item extends Props {

	/** 实例编号 */
	private long id;
	/** 数量 */
	private int quantity;

	public Item(long id, int templateId, int quantity) {
		super();
		this.id = id;
		this.template = ScriptUtil.findItemTemplate(templateId);
		this.quantity = quantity;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	/**
	 * 增加数量
	 * 
	 * @param value
	 * @return
	 */
	public int addQuantity(int value) {
		quantity += value;
		return quantity;
	}

	/**
	 * 减少数量
	 * 
	 * @param value
	 * @return
	 */
	public int deductQuantity(int value) {
		if (value > quantity) {
			throw new IllegalStateException("道具数量不足！");
		}
		quantity -= value;
		return quantity;
	}

	public int getMaxStackQuantity() {
		return ((ItemT) template).getMaxStackQuantity();
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", quantity=" + quantity + ", name="
				+ template.getName() + "]";
	}

}
