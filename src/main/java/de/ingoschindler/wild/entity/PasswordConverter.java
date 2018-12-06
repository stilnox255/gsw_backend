package de.ingoschindler.wild.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import de.rtner.security.auth.spi.SimplePBKDF2;

@Converter
public class PasswordConverter implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(String input) {
		return new SimplePBKDF2().deriveKeyFormatted(input);
	}

	@Override
	public String convertToEntityAttribute(String input) {
		return input;
	}
}