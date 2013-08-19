package me.vivia.game.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RandomUtilTest {

	@Test
	public void testIsHit() {
		assertTrue(RandomUtil.isHit(1, 1));
	}

	@Test
	public void testRandInt() {
		assertTrue(RandomUtil.randomInt(1, 1) == 1);
		int min = 1;
		int max = 2;
		int value = RandomUtil.randomInt(min, max);
		assertTrue(value >= min);
		assertTrue(value < max);
		for (int i = 0; i < 100; i++) {
			value = RandomUtil.randomInt(max, min);
			assertTrue(value >= min);
			assertTrue(value < max);
		}
	}
}
