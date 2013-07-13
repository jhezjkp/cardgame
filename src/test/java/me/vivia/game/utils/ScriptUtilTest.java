package me.vivia.game.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
		List<ItemT> list = ScriptUtil.getAllItemTemplates();
		assertNotNull(list);
		assertTrue(list.size() >= 1);
		for (ItemT t : list) {
			System.out.println(t.getName());
		}
	}

	@Test
	public void testFindItemTemplate() {
		ItemT t = ScriptUtil.findItemTemplate(1);
		assertNotNull(t);
		assertEquals(1, t.getId());
		assertEquals("茅台", t.getName());
	}

}
