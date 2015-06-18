package org.javamoney.midas.javaee7.jpa;

import java.util.Objects;

import javax.money.MonetaryAmount;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.javamoney.moneta.Money;

/**
 * Converter to {@link MonetaryAmount} using the {@link Money} implementation using {@link Money#toString()}
 * and {@link Money#parse(CharSequence)}
 * @author Otavio Santana
 */
@Converter
public class MoneyConverter implements AttributeConverter<MonetaryAmount, String> {

	@Override
	public String convertToDatabaseColumn(MonetaryAmount attribute) {
		
		if (Objects.isNull(attribute)) {
			return null;
		}
		return Money.from(attribute).toString();
	}

	@Override
	public MonetaryAmount convertToEntityAttribute(String dbData){
		if (Objects.isNull(dbData)) {
			return null;
		}
		return Money.parse(dbData);
	}

}