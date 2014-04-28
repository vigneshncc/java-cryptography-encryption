/**
 * 
 */
package org.gvignesh.loader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author arul
 *
 */
public class ApplicationContextLoader {

	  private static final Logger log = Logger.getLogger(ApplicationContextLoader.class);
	  
	  protected ConfigurableApplicationContext applicationContext;

	  public ApplicationContextLoader() {
	  }

	  public ConfigurableApplicationContext getApplicationContext() {
	    return applicationContext;
	  }

	  protected void loadApplicationContext(String... configLocations) {
	    log.debug("Loading application context "+ configLocations);
	    applicationContext = new ClassPathXmlApplicationContext(configLocations);
	    applicationContext.registerShutdownHook();
	  }

	  protected void injectDependencies(Object main) {
	    log.debug("Injecting dependencies...");
	    getApplicationContext().getBeanFactory()
	        .autowireBeanProperties(main, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
	  }

	  public void load(Object main, String... configLocations) {
	    log.debug("Loading...");
	    loadApplicationContext(configLocations);
	    injectDependencies(main);
	  }
}
