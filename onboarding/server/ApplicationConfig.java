package com.payjunction.onboarding.server;

import com.payjunction.onboarding.database.Database;
import com.payjunction.onboarding.database.RequestData;
import com.payjunction.onboarding.encryption.AesEncryption;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.Properties;

/**
 * @author bcantello
 * @since 2019-12-11
 */
public class ApplicationConfig extends ResourceConfig
{
	private final int port;

	public ApplicationConfig(Properties props)
	{
		this(props, new Database());
	}

	ApplicationConfig(Properties props, Database database)
	{
		String aesKey = props.getProperty("aes.key");
		port = Integer.parseInt((String)props.get("server.port"));

		packages("com.payjunction.onboarding.server");
		register(RequestLogFilter.class);
		register(new AbstractBinder()
		{
			@Override
			protected void configure()
			{
				bindAsContract(AesEncryption.class);
				bind(database);
				bindAsContract(RequestData.class);
				bind(aesKey).named("aes.key");
			}
		});
	}

	int getPort()
	{
		return port;
	}
}
