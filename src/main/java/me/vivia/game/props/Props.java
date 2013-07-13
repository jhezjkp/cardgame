package me.vivia.game.props;

import me.vivia.game.common.Quality;

/**
 * 公共抽象物品对象
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public abstract class Props implements IProps {

	protected PropsT template;

	@Override
	public int getTemplateId() {
		return template.getId();
	}

	@Override
	public String getName() {
		return template.getName();
	}

	@Override
	public String getDesc() {
		return template.getDesc();
	}

	@Override
	public Quality getQuality() {
		return template.getQuality();
	}

}
