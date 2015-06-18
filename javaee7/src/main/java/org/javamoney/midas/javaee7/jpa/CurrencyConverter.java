package org.javamoney.midas.javaee7.jpa;

import java.util.Objects;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter to {@link CurrencyUnit} it serializes to {@link String} using the currency code.
 * @author Otavio Santana
 */
@Converter
public class CurrencyConverter implements AttributeConverter<CurrencyUnit, String>{

	@Override
	public String convertToDatabaseColumn(CurrencyUnit attribute) {
		if(Objects.isNull(attribute)) {
			return null;
		}
		return attribute.toString();
	}

	@Override
	public CurrencyUnit convertToEntityAttribute(String dbData) {
		if(Objects.isNull(dbData)){
			return null;
		}
		return Monetary.getCurrency(dbData);
	}

}
