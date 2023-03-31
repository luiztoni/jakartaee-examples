package br.luiztoni.restful.config.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PasswordTest {
	
	@Test
	public void encodedPasswordIsNotNull() {
		var password = Password.encode("pwd", "key");
		System.out.println(password);
		assertNotNull(password);
	}
	
	@Test
	public void validateCorrectPassword() {
		var password = Password.encode("pwd", "key");
		System.out.println(password);
		assertTrue(Password.verify("pwd", password, "key"));
	}
	
	@Test
	public void validateIncorrectPassword() {
		var password = Password.encode("pwd", "key");
		System.out.println(password);
		assertFalse(Password.verify("pwd2", password, "key"));
	}

	
}
