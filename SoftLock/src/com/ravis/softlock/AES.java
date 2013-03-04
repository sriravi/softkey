package com.ravis.softlock;

import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AES {

	private static byte[] key = null;

	private static void init() throws Exception {

		if (key != null)
			return;

		byte[] keyStart = "softlock encryption key".getBytes();
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(keyStart);
		kgen.init(128, sr); // 192 and 256 bits may not be available
		SecretKey skey = kgen.generateKey();
		key = skey.getEncoded();

		/*
		 * 
		 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 * 
		 * Bitmap bm = new Bitmap(); bm.compress(Bitmap.CompressFormat.PNG, 100,
		 * baos); // bm is the bitmap object
		 * 
		 * // byte[] b = baos.toByteArray();
		 * 
		 * // encrypt byte[] encryptedData = encrypt(key,b); // decrypt byte[]
		 * decryptedData = decrypt(key,encryptedData);
		 */
	}

	public static byte[] encrypt(byte[] clear) {
		try {
			init();
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(clear);
			return encrypted;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static byte[] decrypt(byte[] encrypted) {
		try {
			init();
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] decrypted = cipher.doFinal(encrypted);
			return decrypted;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static String toString(byte[] _bytes) {
		String _string = "";

		for (int i = 0; i < _bytes.length; i++) {
			_string += (char) _bytes[i];
		}

		return _string;
	}

}
