package com.vurtnec.component.logger;

import org.slf4j.Logger;

/**
 * logger util
 * @author Vurtnec
 *
 */
public class LoggerUtil {
	
	private static LoggerUtil instance = null;
	
	public static LoggerUtil getInstantce() {
		if(instance == null) {
			synchronized(LoggerUtil.class){
                if(instance == null){
                    instance = new LoggerUtil();
                }
            }
		}
		return instance;
	}

	
	public void debug(Logger logger, String message) {
		if(logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}
	
	public void debug(Logger logger, String message, Object object) {
		if(logger.isDebugEnabled()) {
			logger.debug(message, object);
		}
	}
	
}
