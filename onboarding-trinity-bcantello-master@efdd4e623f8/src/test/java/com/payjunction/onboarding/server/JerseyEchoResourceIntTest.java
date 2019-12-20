package com.payjunction.onboarding.server;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

/**
 * @author bcantello
 * @since 2019-11-21
 */
public class JerseyEchoResourceIntTest
{
	private WebTarget webTarget;

	@ClassRule
	public static final ServerRule serverRule = new ServerRule();

	@Before
	public void setup()
	{
		webTarget =  ClientBuilder.newClient().target("http://localhost:" + serverRule.getPort());
	}

	@Test
	public void testEcho_happyPath_hasStatusCode200()
	{
		Response response = post(new Form().param("echo", "brandon"));

		assertThat(response.readEntity(String.class)).isEqualTo("brandon");
		assertThat(response.getStatus()).isEqualTo(200);
		assertThat(response.getHeaderString("Content-Type")).isEqualTo("text/plain");
	}

	@Test
	public void testEcho_nonPostMethod_hasStatusCode405()
	{
		long statusCode = webTarget.request().get().getStatus();

		assertThat(statusCode).isEqualTo(405);
	}

	@Test
	public void testEcho_emptyString_hasStatusCode400()
	{
		Response response = post(new Form().param("echo", ""));

		assertThat(response.getStatus()).isEqualTo(400);
	}

	@Test
	public void testEcho_nullPost_hasStatusCode400()
	{
		Response response = post(null);

		assertThat(response.getStatus()).isEqualTo(400);
	}

	@Test
	public void testEcho_emptyPost_hasStatusCode400()
	{
		Response response = post(new Form());

		assertThat(response.getStatus()).isEqualTo(400);
	}

	@Test
	public void testEcho_nullEcho_hasStatusCode400()
	{
		Response response = post(new Form().param("echo", null));

		assertThat(response.getStatus()).isEqualTo(400);
	}

	private Response post(Form form)
	{
		return webTarget.request().post(Entity.form(form));
	}
}
