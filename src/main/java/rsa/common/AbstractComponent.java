package rsa.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class AbstractComponent {
	
	public static final Logger log=LogManager.getLogger(); 
	
	public static void main(String[] args) {
				log.info("AbstractComponent");
	}

}
