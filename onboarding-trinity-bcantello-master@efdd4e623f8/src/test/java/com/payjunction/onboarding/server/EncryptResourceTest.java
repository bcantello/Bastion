package com.payjunction.onboarding.server;

import static org.assertj.core.api.Assertions.assertThat;

import com.payjunction.onboarding.encryption.AesEncryption;

import org.junit.Test;

import java.security.GeneralSecurityException;

/**
 * @author bcantello
 * @since 2019-12-10
 */
public class EncryptResourceTest
{
	private final AesEncryption aesEncryption =
			new AesEncryption("O1yH248Uu65o3oCMwROeHuiwMO10Dd2m");

	@Test
	public void testEncrypt_encryptsDataToBase64CipheredText() throws GeneralSecurityException
	{
		String encryptedText = new EncryptResource(aesEncryption).encryptPayload("Brandon");

		assertThat(encryptedText).isNotEqualTo("Brandon");
	}

	@Test
	public void testEncrypt_decryptingEncryptedData_ReturnsOriginalPayload()
			throws GeneralSecurityException
	{
		String encryptedText = new EncryptResource(aesEncryption).encryptPayload("Brandon");
		String decryptedText = new DecryptResource(aesEncryption).decryptData(encryptedText);

		assertThat(decryptedText).isEqualTo("Brandon");
	}
}
