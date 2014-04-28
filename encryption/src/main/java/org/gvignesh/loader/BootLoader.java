/**
 * BootLoader.java
 * com.piqube.loader
 * @author vignesh
 * @date 03-Apr-2014
 * @time 3:43:12 pm
 */
package org.gvignesh.loader;

import org.apache.log4j.Logger;
import org.gvignesh.initiate.Initiate;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author vignesh
 *
 */
public class BootLoader {
    private static final Logger log = Logger.getLogger(BootLoader.class);

    private static BootLoader instance = null;

    private static ConfigurableApplicationContext context = null;

    static {
        instance = new BootLoader();
    }

    private static BootLoader getInstance() {
        return instance;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        log.debug("Bootloader Initializing Encryption . . .");
        ApplicationContextLoader loader = new ApplicationContextLoader();
        String configlocations[] = {"applicationContext-init.xml"};
        loader.loadApplicationContext(configlocations);
        context = loader.getApplicationContext();
        getInstance().run(args);
	}
	
	/**
	 * @param args
	 */
	private void run(String[] args) {
		Initiate initiate = (Initiate) context.getBean("initiate");
		initiate.start();
	}
}