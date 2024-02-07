package com.orderManage.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 共通　ログを管理するクラス TODO
 * @author aocon-mac
 *
 */
@Service
public class OrderManageLoggingService {

//	private static final Logger logger = LoggerFactory.getLogger(OrderManageLoggingService.class);
	private static final Logger logger = LoggerFactory.getLogger("appLogger");
	 
	public void trace(String msg) {
	  logger.trace(msg);
	}
	public void debug(String msg) {
	  logger.debug(msg);
	}
	public void info(String msg) {
	  logger.info(msg);
	}
	public void warn(String msg) {
	  logger.warn(msg);
	}
	public void error(String msg) {
	  logger.error(msg);
	}
}
