package net.drwille.otpauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import net.drwille.otpauth.OTPAuthURI.PasswordType;

public class KeyStoreReader {

	private static final String STORE_FILE_NAME = ".google";

	private KeyStoreReader() {
	}

	public static List<OTPAuthURI> getAuthStrings() throws IOException, URISyntaxException {
		List<OTPAuthURI> retVal = new ArrayList<>();
		Path path = Paths.get(System.getProperty("user.home"), STORE_FILE_NAME);
		try (BufferedReader br = Files.newBufferedReader(path)) {
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				URI uri = new URI(line);
				PasswordType passwordType = PasswordType.valueOf(uri.getAuthority().toUpperCase());
				String email = uri.getPath().split(":")[1];
				for (String query : uri.getQuery().split("&")) {
					String[] parts = query.split("=");
					if (parts.length != 2) {
						continue;
					}
					if ("secret".equals(parts[0])) {
						retVal.add(new OTPAuthURI(passwordType, email, parts[1]));
					}
				}
			}
		}
		return retVal;
	}

}
