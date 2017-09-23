package net.drwille.otpauth;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * HMAC-Based One-Time Password (HTOP) per RFC 4226.
 */
public class HTOP {

	private byte[] key;
	private Mac mac;

	/**
	 * @param key
	 * @param length
	 * @param interval
	 * @throws NoSuchAlgorithmException
	 *             The SHA-1 HMAC cannot be found
	 */
	public HTOP(byte[] key) throws NoSuchAlgorithmException {
		this.key = key;
		this.mac = Mac.getInstance("HMACSHA1");
	}

	public int getTimeBasedCode(int length, int interval) throws InvalidKeyException {
		// Get counter (current time in sec / interval)
		long counter = System.currentTimeMillis() / 1000 / interval;

		return getCounterBasedCode(length, counter);
	}

	public int getCounterBasedCode(int length, long counter) throws InvalidKeyException {
		// Perform HMAC SHA-1 on the counter
		byte[] counterArray = ByteBuffer.allocate(8).putLong(counter).array();
		mac.init(new SecretKeySpec(key, ""));
		byte[] hmac = mac.doFinal(counterArray);

		// Dynamic truncation
		int offsetBits = hmac[hmac.length - 1] & 0xF;
		int code = ByteBuffer.wrap(hmac, offsetBits, 4).getInt() & 0x7FFFFFFF;
		int moduloCode = code % ((int) Math.pow(10, length));
		return moduloCode;
	}
}
