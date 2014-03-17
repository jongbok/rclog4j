/**
 * 
 */
package com.forif.rclog.test;

import org.apache.log4j.Logger;
import com.forif.rclog.agent.*;

/**
 * @author jbpark
 *
 */
public class RCLogTest {
	private static int COUNT = 1;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RCLogManager.start();
		Logger logger1 = Logger.getLogger("com.forif.rclog.agent");
		Logger logger2 = Logger.getLogger("com.forif.rclog");
		Logger logger3 = Logger.getLogger("com.forif");
		
		while(true){
			logger1.debug("logger1 debug message - " + COUNT );
			logger1.info("logger1 info message - " + COUNT );
			logger1.error("logger1 error message - " + COUNT );
			logger2.debug("logger2 debug message - " + COUNT );
			logger2.info("logger2 info message - " + COUNT);
			logger2.error("logger2 error message - " + COUNT);
			logger3.debug("logger3 debug message - " + COUNT);
			logger3.info("logger3 info message - " + COUNT);
			logger3.error("logger3 error message - " + COUNT);
			if(COUNT++ > 60*60)
				break;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}

	}

}
