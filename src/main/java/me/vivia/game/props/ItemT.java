package me.vivia.game.props;

import me.vivia.game.common.ItemUseTarget;
import me.vivia.game.common.Quality;

/**
 * 道具模板
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public class ItemT extends PropsT {

	/** 使用对象 */
	private ItemUseTarget target;
	/** 最大堆叠数 */
	private int maxStackQuantity;
	/** 使用效果函数 */
	private ItemEffect effect;

	public ItemT(int id, String name, String desc, Quality quality,
			int maxStackQuantity, ItemUseTarget target, ItemEffect effect, int icon) {
		super(id, name, desc, quality, icon);
		this.target = target;
		this.maxStackQuantity = maxStackQuantity;
		this.effect = effect;
	}

	public ItemUseTarget getTarget() {
		return target;
	}

	public int getMaxStackQuantity() {
		return maxStackQuantity;
	}

	public ItemEffect getEffect() {
		return effect;
	}

}
