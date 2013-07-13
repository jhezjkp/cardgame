import java.io.IOException;

import clojure.lang.IFn;
import clojure.lang.RT;
import clojure.lang.Var;

/**
 * 临时验证性测试类
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public class MiscTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// clojure验证
		try {
			RT.loadResourceScript("test.clj");
			Var var = RT.var("me.vivia.game.test", "n");
			
			System.out.println(var.deref());
			IFn function = RT.var("me.vivia.game.test", "function");
			System.out.println(function.invoke());
			
			function = RT.var("me.vivia.game.test", "hello");
			System.out.println(function.invoke("vivia"));
			
			function = RT.var("me.vivia.game.test", "加体力");
			System.out.println(function.invoke("vivia"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
