package com.payjunction.onboarding.server;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class JerseyEchoResourceTest
{
	private final JerseyEchoResource jerseyEchoResource = new JerseyEchoResource();

	@Test
	public void testPostEcho()
	{
		assertThat(jerseyEchoResource.postEcho("brandon")).isEqualTo("brandon");
	}

	@Test
	public void testPostEcho_response_empty()
	{
		assertThat(jerseyEchoResource.postEcho("")).isEqualTo("");
	}

	@Test
	public void testPostEcho_response_null()
	{
		assertThat(jerseyEchoResource.postEcho(null)).isEqualTo(null);
	}
}
