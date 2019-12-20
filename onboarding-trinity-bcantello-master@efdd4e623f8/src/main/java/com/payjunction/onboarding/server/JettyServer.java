package com.payjunction.onboarding.server;

import com.payjunction.onboarding.dependency.injection.ApplicationConfig;

import com.google.common.util.concurrent.AbstractIdleService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class JettyServer extends AbstractIdleService
{
	private final Logger log = LoggerFactory.getLogger(JettyServer.class);
	private final Server server;

	public JettyServer(Properties properties)
	{
		int port = Integer.parseInt((String)properties.get("server.port"));

		ApplicationConfig config = new ApplicationConfig(properties);
		ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(config));

		server = new Server(port);
		ServletContextHandler context = new ServletContextHandler(server, "/");
		context.addFilter(RequestIdAndIpFilter.class, "/*", null);
		context.addServlet(jerseyServlet, "/*");

	}

	@Override
	protected void startUp() throws Exception
	{
		server.start();

		log.info("Server started on port={}", getPort());
	}

	@Override
	protected void shutDown() throws Exception
	{
		server.stop();
	}

	public int getPort()
	{
		return ((ServerConnector) server.getConnectors()[0]).getLocalPort();
	}
}
