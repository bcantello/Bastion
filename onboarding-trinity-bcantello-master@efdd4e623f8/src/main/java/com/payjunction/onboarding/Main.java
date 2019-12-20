package com.payjunction.onboarding;

import com.payjunction.onboarding.server.JettyServer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class Main
{
	public static void main(String[] args) throws IOException
	{
		try (InputStream is = JettyServer.class.getClassLoader()
				.getResourceAsStream("onboarding.properties"))
		{
			Properties props = new Properties();
			props.load(is);

			new JettyServer(props).startAsync();
		}
	}
}