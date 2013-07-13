package me.vivia.game.props;

import clojure.lang.IFn;

/**
 * 道具效果
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public class ItemEffect {

	private IFn function;

	public ItemEffect(IFn function) {
		super();
		this.function = function;
	}

	public void use(Object targetObj) throws IllegalArgumentException {
		if (targetObj == null) {
			throw new IllegalArgumentException("使用目标不能为空！");
		}
		function.invoke(targetObj);
	}

}
