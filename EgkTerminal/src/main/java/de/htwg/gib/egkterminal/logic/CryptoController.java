package de.htwg.gib.egkterminal.logic;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import de.htwg.gib.egkterminal.model.CipherObject;

public class CryptoController {

	public CipherObject encrypt(String plaintext, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		SecureRandom secureRandom = new SecureRandom();
		SecretKey secretKey = new SecretKeySpec(key, "AES");
		byte[] nonce = new byte[12];
		secureRandom.nextBytes(nonce);
		final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec parameterSpec = new GCMParameterSpec(128, nonce);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);
		byte[] cipherText = cipher.doFinal(plaintext.getBytes());
		String cipherText64 = Base64.getEncoder().encodeToString(cipherText);
		String nonce64 = Base64.getEncoder().encodeToString(nonce);
		return new CipherObject(cipherText64, nonce64);
	}

	public byte[] createRandomKey() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] key = new byte[32];
		secureRandom.nextBytes(key);
		return key;
	}

}
