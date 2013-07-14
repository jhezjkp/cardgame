package me.vivia.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;
import java.util.Random;

import me.vivia.game.common.Const;
import me.vivia.game.props.Item;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerTest {

	private IPlayer player;
	// id生成,凑合着用
	private Random random = new Random();

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
		// 测试获得体力
		int energy = 100;
		int gainValue1 = 10;
		int gainValue2 = 200;
		Field field = FieldUtils.getDeclaredField(Player.class, "energy", true);
		field.set(player, energy);

		assertEquals(energy + gainValue1, player.gainEnergy(gainValue1, false));

		assertEquals(Const.MAX_ENERGY,
				player.gainEnergy(Const.MAX_ENERGY, false));

		assertEquals(Const.MAX_ENERGY + gainValue2,
				player.gainEnergy(gainValue2, true));
	}

	@Test(expected = IllegalStateException.class)
	public void testDeductEnergy_throwException() {
		// 测试过量扣除体力
		player.deductEnergy(Const.MAX_ENERGY * 2);
	}

	@Test
	public void testDeductEnergy() {
		// 测试正常扣除体力
		int deductValue = 10;
		assertEquals(Const.MAX_ENERGY - deductValue,
				player.deductEnergy(deductValue));
	}

	@Test
	public void testAddProps() {
		// 测试增加道具
		PackController packController = player.getPackController();
		int templateId = 1;
		int quantity = 1;
		Item item = new Item(random.nextLong(), templateId, quantity);
		long id = packController.addProps(item);
		assertEquals(item.getId(), id);
		Item existItem = packController.findItem(item.getId());
		assertNotNull(existItem);
		assertEquals(templateId, existItem.getTemplateId());
		assertEquals(quantity, existItem.getQuantity());

		item = new Item(random.nextLong(), templateId, quantity);
		long newId = packController.addProps(item);
		assertEquals(id, newId);
		existItem = packController.findItem(id);
		assertNotNull(existItem);
		assertEquals(templateId, existItem.getTemplateId());
		assertEquals(quantity + quantity, existItem.getQuantity());

		int newQuantity = 100; // 茅台最大堆叠数为99
		item = new Item(random.nextLong(), templateId, newQuantity);
		newId = packController.addProps(item);
		assertEquals(item.getId(), newId);
		// 原来的2瓶茅台将再堆叠进97瓶
		existItem = packController.findItem(id);
		assertNotNull(existItem);
		assertEquals(templateId, existItem.getTemplateId());
		assertEquals(99, existItem.getQuantity());
		// 剩下3瓶无法堆叠到现有道具中
		existItem = packController.findItem(newId);
		assertNotNull(existItem);
		assertEquals(templateId, existItem.getTemplateId());
		assertEquals(3, existItem.getQuantity());
		
		//增加银两
		int money = 10000;
		item = new Item(random.nextLong(), Const.TEMPLATE_ID_MONEY, money);
		newId = packController.addProps(item);
		assertEquals(-1, newId);
		assertEquals(money, player.getMoney());
	}
}
