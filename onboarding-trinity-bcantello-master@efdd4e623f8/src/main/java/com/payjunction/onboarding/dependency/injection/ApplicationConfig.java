package com.payjunction.onboarding.dependency.injection;

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
	public ApplicationConfig(Properties props)
	{
		String aesKey = props.getProperty("aes.key");

		packages("com.payjunction.onboarding.server");
		register(new AbstractBinder()
		{
			@Override
			protected void configure()
			{
				bindAsContract(AesEncryption.class);
				bind(aesKey).named("aes.key");
			}
		});
	}
}
