package com.payjunction.onboarding.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author bcantello
 * @since 2019-11-25
 */
@Path("/")
public class JerseyEchoResource
{
	private final Logger log = LoggerFactory.getLogger(JerseyEchoResource.class);

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String postEcho(@NotEmpty @FormParam("echo") String echoValue)
	{
		log.info("received echo={}", echoValue);

		return echoValue;
	}
}
