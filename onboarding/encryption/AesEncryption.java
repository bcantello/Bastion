package com.payjunction.onboarding.encryption;

import com.google.common.base.Charsets;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author bcantello
 * @since 2019-12-12
 */
public class AesEncryption
{
	private final String aesKey;

	@Inject
	public AesEncryption(@Named("aes.key") String aesKey)
	{
		this.aesKey = aesKey;
	}

	public String encrypt(String plainText) throws GeneralSecurityException
	{
		SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey.getBytes(Charsets.UTF_8), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

		byte[] ivBytes = cipher.getIV();
		byte[] cypherTextBytes = cipher.doFinal(plainText.getBytes(Charsets.UTF_8));
		byte[] combined = new byte[ivBytes.length + cypherTextBytes.length];
		System.arraycopy(ivBytes, 0, combined, 0, ivBytes.length);
		System.arraycopy(cypherTextBytes, 0, combined, ivBytes.length, cypherTextBytes.length);

		return Base64.getEncoder().encodeToString(combined);
	}

	public String decrypt(String cipherText) throws GeneralSecurityException
	{
		byte[] encryptedCombinedBytes = Base64.getDecoder().decode(cipherText);
		byte[] ivBytes = Arrays.copyOfRange(encryptedCombinedBytes, 0, 16);

		SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey.getBytes(Charsets.UTF_8), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(ivBytes));

		byte[] cipherTextBytes =
				Arrays.copyOfRange(encryptedCombinedBytes, 16, encryptedCombinedBytes.length);
		byte[] decryptedTextBytes = cipher.doFinal(cipherTextBytes);

		return new String(decryptedTextBytes, StandardCharsets.UTF_8);
	}
}
