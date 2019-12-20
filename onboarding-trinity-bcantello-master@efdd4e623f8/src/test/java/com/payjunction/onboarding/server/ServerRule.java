package com.payjunction.onboarding.server;

import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author bcantello
 * @since 2019-11-22
 */
public class ServerRule extends ExternalResource
{
	private final Logger log = LoggerFactory.getLogger(ServerRule.class);
	private JettyServer jettyServer;
	private Integer port;

	@Override
	protected void before()
	{
		Properties properties = new Properties();
		properties.setProperty("server.port", "0");
		properties.setProperty("aes.key", "O1yH248Uu65o3oCMwROeHuiwMO10Dd2m");

		jettyServer = new JettyServer(properties);
		jettyServer.startAsync().awaitRunning();

		port = jettyServer.getPort();
		log.info("Server started on port={}", port);
	}

	@Override
	protected void after()
	{
		jettyServer.stopAsync().awaitTerminated();
		log.info("Server stopped");
	}

	Integer getPort()
	{
		return port;
	}
}
