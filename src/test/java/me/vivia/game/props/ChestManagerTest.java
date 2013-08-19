package me.vivia.game.props;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Random;

import me.vivia.game.common.Quality;
import me.vivia.game.props.ChestManager.ChestKey;
import me.vivia.game.utils.RandomUtil;
import me.vivia.game.utils.ScriptUtil;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChestManagerTest {

	@Before
	public void setUp() {
		RandomUtil.setRandom(new Random() {

			int[] result = new int[] { 0, 1, 2, 3, 10, 20, 30, 40, 50, 60, 70,
					80, 90, 99 };
			int index = 0;

			@Override
			public int nextInt(int n) {
				int r = result[index++];
				if (r >= n) {
					r = n - 1;
				}
				return r;
			}

		});
	}

	@After
	public void tearDown() {
		RandomUtil.setRandom(new Random());
	}

	@Test
	public void testGenProps_1() {
		// 全服随机宝箱，多个产出
		ChestT template = ScriptUtil.findChestTemplate(1);
		List<Props> list = ChestManager.getInstance().genProps(template);
		assertNotNull(list);
		assertEquals(2, list.size());
		Props props = list.get(0);
		assertEquals(99, props.getTemplateId()); // 银两
		assertEquals(100, props.getQuantity());
		assertEquals(Quality.Blue, props.getQuality());
		props = list.get(1);
		assertEquals(1, props.getTemplateId()); // 茅台
		assertEquals(3, props.getQuantity());
		assertEquals(Quality.Blue, props.getQuality());
	}

	@Test
	public void testGenProps_2() {
		// 全服随机宝箱，单个产出
		ChestT template = ScriptUtil.findChestTemplate(2);
		List<Props> list = ChestManager.getInstance().genProps(template);
		assertNotNull(list);
		assertEquals(1, list.size());
		Props props = list.get(0);
		assertEquals(99, props.getTemplateId()); // 银两
		assertEquals(100, props.getQuantity());
		assertEquals(Quality.Blue, props.getQuality());
	}

	@Test
	public void testGenProps_real() throws IllegalAccessException {
		// 实测，真实随机
		RandomUtil.setRandom(new Random());
		// 全服随机宝箱，多个产出
		ChestT template = ScriptUtil.findChestTemplate(1);
		for (int i = 0; i < 10000; i++) {
			List<Props> list = ChestManager.getInstance().genProps(template);
			assertNotNull(list);
			assertFalse(list.isEmpty());
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < list.size(); j++) {
				Props props = list.get(j);
				sb.append(props.getName()).append("x")
						.append(props.getQuantity()).append(" ");
			}
			System.out.println(sb.toString());
		}
		// 宝箱1中的候选库2只出产100份
		Map<ChestKey, Integer> map = (Map<ChestKey, Integer>) FieldUtils
				.readDeclaredField(ChestManager.getInstance(), "map", true);
		int count = map.get(new ChestManager.ChestKey(1, 2));
		System.out.println("限制物品出产量：" + count);
		assertTrue(count <= 100);
	}
}
