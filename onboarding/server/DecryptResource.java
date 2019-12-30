package com.payjunction.onboarding.server;

import com.payjunction.onboarding.encryption.AesEncryption;

import java.security.GeneralSecurityException;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * @author bcantello
 * @since 2019-12-10
 */
@Path("/decrypt")
public class DecryptResource
{
	private final AesEncryption aesEncryption;

	@Inject
	public DecryptResource(AesEncryption aesEncryption)
	{
		this.aesEncryption = aesEncryption;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String decryptData(@NotEmpty String encryptedData)
	{
		try
		{
			return aesEncryption.decrypt(encryptedData);
		}
		catch (IllegalArgumentException | GeneralSecurityException e)
		{
			throw new WebApplicationException("Invalid Encrypted Payload", 422);
		}
	}
}
