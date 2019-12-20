package com.payjunction.onboarding.server;

import com.payjunction.onboarding.encryption.AesEncryption;

import java.security.GeneralSecurityException;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author bcantello
 * @since 2019-11-25
 */
@Path("/encrypt")
public class EncryptResource
{
	private final AesEncryption aesEncryption;

	@Inject
	public EncryptResource(AesEncryption aesEncryption)
	{
		this.aesEncryption = aesEncryption;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String encryptPayload(@NotEmpty String payload) throws GeneralSecurityException
	{
		return aesEncryption.encrypt(payload);
	}
}
