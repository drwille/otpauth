/**
 * Copyright (C) Daniel Wille, 2017
 */
package net.drwille.otpauth;

public class OTPAuthURI {

	public enum PasswordType {
		TOTP
	};

	PasswordType passwordType;
	String email;
	String secret;

	public OTPAuthURI(PasswordType passwordType, String email, String secret) {
		this.passwordType = passwordType;
		this.email = email;
		this.secret = secret;
	}

	public PasswordType getPasswordType() {
		return passwordType;
	}

	public String getEmail() {
		return email;
	}

	public String getSecret() {
		return secret;
	}
}
