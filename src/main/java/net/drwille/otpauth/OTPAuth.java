package net.drwille.otpauth;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

public class OTPAuth {

	public static void main(String[] args) throws IOException, URISyntaxException, ParseException, InvalidKeyException, NoSuchAlgorithmException {
		List<OTPAuthURI> authURIs = KeyStoreReader.getAuthStrings();
		for (OTPAuthURI authURI : authURIs) {
			byte[] key = Base32String.decode(authURI.getSecret());
			int code = new HTOP(key).getTimeBasedCode(6, 30);
			System.out.println(code + "  " + authURI.email);
		}
	}

}
