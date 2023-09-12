package com.mailtoDesenvolper.loginspring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;

import java.util.Base64;
import java.util.Date;

@SpringBootTest
class LoginSpringApplicationTests {

	@Test
	void createToken() throws Exception{
		var service = new KeyBasedPersistenceTokenService();
		service.setServerSecret("SECRET1234");
		service.setServerInteger(22);
		service.setSecureRandom(new SecureRandomFactoryBean().getObject());

		var token = service.allocateToken("vitin@gmail.com");

		System.out.println("Email: " + token.getExtendedInformation());
		System.out.println("Data: " + new Date(token.getKeyCreationTime()));
		System.out.println("Token Transparente: " + token.getKey());

	}

	@Test
	public void readToken() throws Exception {
		var service = new KeyBasedPersistenceTokenService();
		service.setServerSecret("SECRET1234");
		service.setServerInteger(22);
		service.setSecureRandom(new SecureRandomFactoryBean().getObject());

		var rawToken = "MTY5NDQ5MTgzMDYyNTo2NDU5ODlmYjMyZTkzMTY2Mzk3MDZhODQ1NTUwYTM2NzkyNTdkNmRjZjcwOGU1MzZmY2U3YmI5MmI4ODZlY2IyOnZpdGluQGdtYWlsLmNvbTphZTBlOGZlYTFiYmQyNzIxM2I5OTAyMjljMDdjMTA5NTk3NDhlZTIyNGIxNTVjODliYmNmYzgzYWVkNGYxMGJkYzYzZWY4MjBhNzViYzVjZjI4MDdlMjJkZTQ4ZDViM2NmZGVkMDAxNjU1ODA0M2M0NzI1ZjliMmRjZmRlMTU4MA==";

		var token = service.verifyToken(rawToken);

		System.out.println(token.getExtendedInformation());
		System.out.println(new Date(token.getKeyCreationTime()));
		System.out.println(token.getKey());

	}

	@Test
	public void readPublicTokenInfo() throws Exception {
		var rawToken = "MTY5NDQ5MTgzMDYyNTo2NDU5ODlmYjMyZTkzMTY2Mzk3MDZhODQ1NTUwYTM2NzkyNTdkNmRjZjcwOGU1MzZmY2U3YmI5MmI4ODZlY2IyOnZpdGluQGdtYWlsLmNvbTphZTBlOGZlYTFiYmQyNzIxM2I5OTAyMjljMDdjMTA5NTk3NDhlZTIyNGIxNTVjODliYmNmYzgzYWVkNGYxMGJkYzYzZWY4MjBhNzViYzVjZjI4MDdlMjJkZTQ4ZDViM2NmZGVkMDAxNjU1ODA0M2M0NzI1ZjliMmRjZmRlMTU4MA==";

		var bytes = Base64.getDecoder().decode(rawToken);
		var rawTokenDecoded = new String(bytes);

		System.out.println(rawTokenDecoded);

		String[] tokenParts = rawTokenDecoded.split(":");
		Long timestamp = Long.parseLong(tokenParts[0]);
		var tempo = new Date(timestamp);
		System.out.println("Data: " + tempo);
		System.out.println("Nome: " + tokenParts[2]);

	}

}
