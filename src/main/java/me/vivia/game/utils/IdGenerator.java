package me.vivia.game.utils;

import java.util.Random;

/**
 * id生成器(fake, DO NOT use in production environment!!!)
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public class IdGenerator {

	private static final Random random = new Random();

	private IdGenerator() {
	}

	public static final long getId() {
		return random.nextLong();
	}

}
