package com.smart.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;
import org.logicalcobwebs.proxool.configuration.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxoolInitListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory
			.getLogger(ProxoolInitListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ProxoolFacade.shutdown();
		System.out.println("destroy database pool....");
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		String appDir = contextEvent.getServletContext()
				.getRealPath("/WEB-INF");
		try {
			PropertyConfigurator.configure(appDir + File.separator + "conf"
					+ File.separator + "proxool.properties");
		} catch (ProxoolException e) {
			logger.error("proxool init error:" + e); // To change body of catch
														// statement use File |
														// Settings | File
														// Templates.
			return;
		}
	}
}
