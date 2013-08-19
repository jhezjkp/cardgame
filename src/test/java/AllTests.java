import me.vivia.game.PlayerTest;
import me.vivia.game.props.ChestManagerTest;
import me.vivia.game.props.ItemTest;
import me.vivia.game.utils.RandomUtilTest;
import me.vivia.game.utils.ScriptUtilTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PlayerTest.class, ChestManagerTest.class, ItemTest.class,
		RandomUtilTest.class, ScriptUtilTest.class })
public class AllTests {

}
