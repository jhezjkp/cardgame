package me.vivia.game.props;

import me.vivia.game.common.ChestRandomScope;

/**
 * 随机宝箱模板
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public class ChestT {

	/** 编号 */
	private int id;
	/** 随机范围类型 */
	private ChestRandomScope scope;
	/**
	 * 候选物品出产配置<br/>
	 * [x][0]-候选库编号<br/>
	 * [x][1]-出产总量(全局随机时，0为不限制出产量)或保底出产数(玩家随机时)<br/>
	 * [x][2]出产机率
	 */
	private int[][] candidateConfig;
	/** 是否出产多个物品 */
	private boolean multiProduce;

	public ChestT(int id, ChestRandomScope scope, int[][] candidateConfig,
			boolean multiProduce) {
		super();
		this.id = id;
		this.scope = scope;
		this.candidateConfig = candidateConfig;
		this.multiProduce = multiProduce;
	}

	public int getId() {
		return id;
	}

	public ChestRandomScope getScope() {
		return scope;
	}

	public int[][] getCandidateConfig() {
		return candidateConfig;
	}

	public boolean isMultiProduce() {
		return multiProduce;
	}

}
