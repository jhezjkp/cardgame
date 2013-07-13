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
	public void testItemUse() {
		Player player = new Player();
		ItemT t = ScriptUtil.findItemTemplate(1);
		t.getEffect().use(player);
		assertEquals(Const.MAX_ENERGY+40, player.getEnergy());
	}

}
