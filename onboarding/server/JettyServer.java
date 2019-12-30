package com.payjunction.onboarding.server;

import com.google.common.util.concurrent.AbstractIdleService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JettyServer extends AbstractIdleService
{
	private final Logger log = LoggerFactory.getLogger(JettyServer.class);
	private final Server server;

	public JettyServer(ApplicationConfig config)
	{
		ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(config));

		server = new Server(config.getPort());
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
