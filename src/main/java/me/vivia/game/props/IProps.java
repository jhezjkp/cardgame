package me.vivia.game.props;

import me.vivia.game.common.Quality;

/**
 * 物品接口，作为道具和装备接口的父接口
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public interface IProps {

	/**
	 * 获取物品实例编号
	 * 
	 * @return
	 */
	long getId();

	/**
	 * 获取物品数量
	 * 
	 * @return
	 */
	int getQuantity();

	/**
	 * 获取物品模板编号
	 * 
	 * @return
	 */
	int getTemplateId();

	/**
	 * 获取名称
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 获取描述
	 * 
	 * @return
	 */
	String getDesc();

	/**
	 * 获取品质
	 * 
	 * @return
	 */
	Quality getQuality();
}
