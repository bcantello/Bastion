package com.payjunction.onboarding.server;

import static org.assertj.core.api.Assertions.assertThat;

import com.payjunction.onboarding.encryption.AesEncryption;

import org.junit.Test;

import java.security.GeneralSecurityException;

/**
 * @author bcantello
 * @since 2019-12-13
 */
public class DecryptResourceTest
{
	private final AesEncryption aesEncryption =
			new AesEncryption("O1yH248Uu65o3oCMwROeHuiwMO10Dd2m");

	@Test
	public void testDecrypt_decryptsBase64CipheredText() throws GeneralSecurityException
	{
		String decryptedText = new DecryptResource(aesEncryption)
				.decryptData("wWeKKwL9YqvrIHwmIrDZXbD22PvVN5FFQ5ngQmxZgyg=");

		assertThat(decryptedText).isEqualTo("Brandon");
	}
}
