package de.ingoschindler.wild.entity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@Converter
public class PasswordConverter implements AttributeConverter<String, String> {

	@Inject
	private Pbkdf2PasswordHash passwordHash;

	@Override
	public String convertToDatabaseColumn(String input) {

		Map<String, String> parameters = new HashMap<>();
		parameters.put("Pbkdf2PasswordHash.Iterations", "3072");
		parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
		parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
		passwordHash.initialize(parameters);

		return passwordHash.generate(input.toCharArray());
	}

	@Override
	public String convertToEntityAttribute(String input) {
		return input;
	}
}