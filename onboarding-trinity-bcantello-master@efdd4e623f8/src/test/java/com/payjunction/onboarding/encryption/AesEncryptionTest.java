package com.payjunction.onboarding.encryption;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import java.security.GeneralSecurityException;

/**
 * @author bcantello
 * @since 2019-12-12
 */
public class AesEncryptionTest
{
	private final AesEncryption aesEncryption =
			new AesEncryption("O1yH248Uu65o3oCMwROeHuiwMO10Dd2m");

	@Test
	public void testEncrypt_uniquePayloadForEachEncrypt() throws GeneralSecurityException
	{
		assertThat(aesEncryption.encrypt("Brandon")).isNotEqualTo(aesEncryption.encrypt("Brandon"));
	}

	@Test
	public void testAesEncryption_decrypt() throws GeneralSecurityException
	{
		String decryptedPayload =
				aesEncryption.decrypt("Xt96mrpS7ynoJUqiMZFZF1omVfc/f+vtFfkzJLe8u6U=");

		assertThat(decryptedPayload).isEqualTo("Brandon");
	}

	@Test
	public void testAesEncryption_encryptPayload_ThenDecryptToOriginalPayload()
			throws GeneralSecurityException
	{
		/**
		 * AES encryption returns a different result each time the same payload is encrypted.
		 * This makes it impossible to assert an expected outcome for an encryption test.
		 * The above encryption test illustrates this fact, and the decryption test shows that AES
		 * encrypted Base64 Strings can be decrypted to the original payload. This roundTrip test
		 * builds on that to show that the encrypt method encrypts a payload into a format that can
		 * then be retrieved by the decrypt method. In this way the encrypt method is implicitly
		 * tested.
		 */

		String encryptedPayload = aesEncryption.encrypt("Brandon");
		String decryptedPayload = aesEncryption.decrypt(encryptedPayload);

		assertThat(decryptedPayload).isEqualTo("Brandon");
	}
}
