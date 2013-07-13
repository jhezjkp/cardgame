package me.vivia.game;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import me.vivia.game.common.Const;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerTest {

	private IPlayer player;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		player = new Player();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGainEnery() throws IllegalArgumentException,
			IllegalAccessException {
		int energy = 100;
		int gainValue1 = 10;
		int gainValue2 = 200;
		Field field = FieldUtils.getDeclaredField(Player.class, "energy", true);
		field.set(player, energy);

		assertEquals(energy + gainValue1, player.gainEnery(gainValue1, false));

		assertEquals(Const.MAX_ENERGY,
				player.gainEnery(Const.MAX_ENERGY, false));

		assertEquals(Const.MAX_ENERGY + gainValue2,
				player.gainEnery(gainValue2, true));
	}

	@Test(expected = IllegalStateException.class)
	public void testDeductEnergy_throwException() {
		player.deductEnergy(Const.MAX_ENERGY * 2);
	}

	@Test
	public void testDeductEnergy() {
		int deductValue = 10;
		assertEquals(Const.MAX_ENERGY - deductValue,
				player.deductEnergy(deductValue));
	}

}
