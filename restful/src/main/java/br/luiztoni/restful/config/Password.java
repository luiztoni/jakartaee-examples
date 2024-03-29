package br.luiztoni.restful.config;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class Password {

	final static Logger logger = Logger.getLogger(Password.class.getSimpleName());

	private static final Random RANDOM = new SecureRandom();
	private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int ITERATIONS = 10000;
	private static final int KEY_LENGTH = 256;

	public static String generateSalt(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			returnValue.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
		}
		return returnValue.toString();
	}

	private static byte[] hash(char[] password, byte[] salt) {
		PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
		Arrays.fill(password, Character.MIN_VALUE);
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			return factory.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
			logger.log(Level.SEVERE,"hash error: {0}", exception.getMessage());
			throw new AssertionError("hash error: " + exception.getMessage(), exception);
		} finally {
			spec.clearPassword();
		}
	}

	public static String encode(String password, String salt) {
		byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
		return Base64.getEncoder().encodeToString(securePassword);
	}

	public static boolean verify(String providedPassword, String securedPassword, String salt) {
		String newSecurePassword = encode(providedPassword, salt);
		return newSecurePassword.equalsIgnoreCase(securedPassword);
	}
}
