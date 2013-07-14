package me.vivia.game.props;

import static org.junit.Assert.assertEquals;
import me.vivia.game.Player;
import me.vivia.game.common.Const;
import me.vivia.game.utils.ScriptUtil;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ItemTest {

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
	public void testItemUse_1() {
		// 测试茅台使用
		Player player = new Player();
		ItemT t = ScriptUtil.findItemTemplate(1);
		t.getEffect().use(player);
		assertEquals(Const.MAX_ENERGY + 40, player.getEnergy());
	}

	@Test
	public void testItemUse_101() {
		// 测试新手礼包使用
		Player player = new Player();
		ItemT t = ScriptUtil.findItemTemplate(101);
		t.getEffect().use(player.getPackController());
		assertEquals(10000, player.getMoney());
		assertEquals(
				5,
				player.getPackController().getPropsQuantity(
						Const.TEMPLATE_ID_WINE));
	}

}
