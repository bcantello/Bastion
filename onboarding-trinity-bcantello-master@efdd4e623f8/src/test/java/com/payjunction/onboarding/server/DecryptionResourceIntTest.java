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
public class DecryptionResourceIntTest
{
	private WebTarget webTarget;

	@ClassRule
	public static final ServerRule serverRule = new ServerRule();

	@Before
	public void setup()
	{
		webTarget =  ClientBuilder.newClient().target(
				"http://localhost:" + serverRule.getPort() + "/decrypt");
	}

	@Test
	public void testDecryptData_ReturnsDecryptedValue_hasStatusCode200()
	{
		Response response = post("Xqc6T/E09cI7IoZSqSi/uVA88C3t5a2sIIPUkVGoSWg=");

		assertThat(response.readEntity(String.class)).isEqualTo("This works");
		assertThat(response.getStatus()).isEqualTo(200);
	}

	@Test
	public void testDecryptData_nonPostMethod_hasStatusCode405()
	{
		long statusCode = webTarget.request().get().getStatus();

		assertThat(statusCode).isEqualTo(405);
	}

	@Test
	public void testDecryptData_nullPost_hasStatusCode400()
	{
		Response response = post(null);

		assertThat(response.getStatus()).isEqualTo(400);
	}

	@Test
	public void testDecryptData_emptyPost_hasStatusCode400()
	{
		Response response = post("");

		assertThat(response.getStatus()).isEqualTo(400);
	}

	@Test
	public void testDecryptData_NonBase64Post_hasStatusCode422()
	{
		Response response = post("NOT BASE64");

		assertThat(response.getStatus()).isEqualTo(422);
	}

	@Test
	public void testDecryptData_NonAesEncryptedBase64Post_hasStatusCode422()
	{
		Response response = post("bGthdXNoZ2xrdWFzaGdkbGs=");

		assertThat(response.getStatus()).isEqualTo(422);
	}

	private Response post(String encryptedData)
	{
		return webTarget.request().post(Entity.text(encryptedData));
	}
}
