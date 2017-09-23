/**
 * Copyright (C) Daniel Wille, 2017
 */
package net.drwille.otpauth;

import java.text.ParseException;

public class Base32String {

	public static byte[] decode(String encodedString) throws ParseException {
		encodedString = encodedString.trim().toLowerCase();

		// Each character encodes 5 bits; 8 bits per byte
		int decodedSizeBytes = encodedString.length() * 5 / 8;
		byte[] decodedBytes = new byte[decodedSizeBytes];

		int bitBuffer = 0;
		int bitCount = 0;
		int decodePos = 0;

		for (int i = 0; i < encodedString.length(); i++) {
			char c = encodedString.charAt(i);
			if (c >= 'a' && c <= 'z') {
				// 'a'-'z' are 0-25
				bitBuffer <<= 5;
				bitBuffer |= (c - 'a');
			} else if (c >= '2' && c <= '7') {
				// '2'-'7' are 26-31
				bitBuffer <<= 5;
				bitBuffer |= (c - '2' + 26);
			} else {
				throw new ParseException("Invalid character '" + c + "'", i);
			}

			bitCount += 5;
			if (bitCount >= 8) {
				// Shift available bits down until we just have 8, and they're in the lowest
				// position. The cast is necessary not just because of the array type, but also
				// to mask out the older data which is still in the higher order bits of the
				// integer we're using as a buffer.
				decodedBytes[decodePos++] = (byte) (bitBuffer >> (bitCount - 8));
				bitCount -= 8;
			}
		}

		return decodedBytes;
	}

}
