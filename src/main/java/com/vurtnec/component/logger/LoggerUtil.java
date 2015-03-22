package com.vurtnec.component.logger;

import org.slf4j.Logger;

import com.google.common.base.MoreObjects;
import com.vurtnec.component.util.Objects;

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
	
	public void debug(Logger logger, Object object) {
		debug(logger, Objects.toString(object));
	}
	
	public void debug(Logger logger, Object object, boolean overRideToString) {
		if(overRideToString) {
			debug(logger, MoreObjects.toStringHelper(object));
		}else {
			debug(logger, Objects.toString(object));
		}
	}
	
	public void debug(Logger logger, String message, Object object) {
		if(logger.isDebugEnabled()) {
			logger.debug(message, object);
		}
	}
	
}
