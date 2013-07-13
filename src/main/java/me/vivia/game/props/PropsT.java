package me.vivia.game.props;

import me.vivia.game.common.Quality;

/**
 * 物品公共抽象模板
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public abstract class PropsT {
	/** 模板编号 */
	protected int id;
	/** 名称 */
	protected String name;
	/** 描述 */
	protected String desc;
	/** 品质 */
	protected Quality quality;
	/** 图标编号 */
	protected int icon;

	public PropsT(int id, String name, String desc, Quality quality, int icon) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.quality = quality;
		this.icon = icon;
	}

	// 模板数据不允许变更，故只有getters

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public Quality getQuality() {
		return quality;
	}

	public int getIcon() {
		return icon;
	}

}
