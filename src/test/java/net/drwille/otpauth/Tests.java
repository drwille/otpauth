/**
 * Copyright (C) Daniel Wille, 2017
 */
package net.drwille.otpauth;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

public class Tests {
	/**
	 * From Appendix D of the RFC
	 * @throws ParseException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void hotp() throws ParseException, InvalidKeyException, NoSuchAlgorithmException {
		byte[] key = "12345678901234567890".getBytes();
		Assert.assertEquals(755224, new HTOP(key).getCounterBasedCode(6, 0));
		Assert.assertEquals(287082, new HTOP(key).getCounterBasedCode(6, 1));
		Assert.assertEquals(359152, new HTOP(key).getCounterBasedCode(6, 2));
		Assert.assertEquals(969429, new HTOP(key).getCounterBasedCode(6, 3));
		Assert.assertEquals(338314, new HTOP(key).getCounterBasedCode(6, 4));
		Assert.assertEquals(254676, new HTOP(key).getCounterBasedCode(6, 5));
		Assert.assertEquals(287922, new HTOP(key).getCounterBasedCode(6, 6));
		Assert.assertEquals(162583, new HTOP(key).getCounterBasedCode(6, 7));
		Assert.assertEquals(399871, new HTOP(key).getCounterBasedCode(6, 8));
		Assert.assertEquals(520489, new HTOP(key).getCounterBasedCode(6, 9));
	}
}
