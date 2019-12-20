package com.payjunction.onboarding.server;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * @author bcantello
 * @since 2019-12-10
 */
public class EncryptionResourceIntTest
{
	private WebTarget webTarget;

	@ClassRule
	public static final ServerRule serverRule = new ServerRule();

	@Before
	public void setup()
	{
		webTarget =  ClientBuilder.newClient().target(
				"http://localhost:" + serverRule.getPort() + "/encrypt");
	}

	@Test
	public void testEncryptPayload_ReturnsEncryptedValue_HasStatusCode200()
	{
		Response response = post("Brandon");

		assertThat(response.getStatus()).isEqualTo(200);
		assertThat(response.readEntity(String.class)).isNotEqualTo("Brandon");
	}

	@Test
	public void testEncryptPayload_nonPostMethod_hasStatusCode405()
	{
		long statusCode = webTarget.request().get().getStatus();

		assertThat(statusCode).isEqualTo(405);
	}

	@Test
	public void testEncryptPayload_nullPost_hasStatusCode400()
	{
		Response response = post(null);

		assertThat(response.getStatus()).isEqualTo(400);
	}

	@Test
	public void testEncryptPayload_emptyPost_hasStatusCode400()
	{
		Response response = post("");

		assertThat(response.getStatus()).isEqualTo(400);
	}

	private Response post(String data)
	{
		return webTarget.request().post(Entity.text(data));
	}
}
