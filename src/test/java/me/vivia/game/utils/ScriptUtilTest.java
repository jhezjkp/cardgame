package me.vivia.game.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import me.vivia.game.common.ChestRandomScope;
import me.vivia.game.props.CandidateT;
import me.vivia.game.props.ChestT;
import me.vivia.game.props.ItemT;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ScriptUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllItemTemplates() {
		// 测试获取所有道具模板
		List<ItemT> list = ScriptUtil.getAllItemTemplates();
		assertNotNull(list);
		assertTrue(list.size() >= 1);
		for (ItemT t : list) {
			System.out.println(t.getName());
		}
	}

	@Test
	public void testFindItemTemplate() {
		// 测试查找道具模板
		ItemT t = ScriptUtil.findItemTemplate(1);
		assertNotNull(t);
		assertEquals(1, t.getId());
		assertEquals("茅台", t.getName());
	}

	@Test
	public void testFindCandidateTemplate() {
		// 测试查找物品候选库
		CandidateT t = ScriptUtil.findCandidateTemplate(1);
		assertNotNull(t);
		assertEquals(1, t.getId());
	}

	@Test
	public void testIsItem() {
		// 测试指定模板编号是否道具模板
		assertTrue(ScriptUtil.isItem(1));
		assertTrue(ScriptUtil.isItem(10));
		assertTrue(ScriptUtil.isItem(499));
		assertEquals(false, ScriptUtil.isItem(500));
		assertEquals(false, ScriptUtil.isItem(0));
	}

	@Test
	public void testGetAllChestTemplates() {
		// 测试获取所有的宝箱模板
		List<ChestT> list = ScriptUtil.getAllChestTemplates();
		assertNotNull(list);
		ChestT t = list.get(0);
		assertEquals(1, t.getId());
		assertEquals(ChestRandomScope.GlobalScope, t.getScope());
		assertEquals(true, t.isMultiProduce());
		// [[1 0 70] [2 100 30]]
		int[][] config = new int[2][3];
		config[0] = new int[] { 1, 0, 70 };
		config[1] = new int[] { 2, 100, 30 };
		assertArrayEquals(config, t.getCandidateConfig());

		assertEquals(t, ScriptUtil.findChestTemplate(t.getId()));
	}

}
