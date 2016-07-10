package org.javamoney.midas.javaee7.jpa;

import javax.money.MonetaryAmount;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.javamoney.moneta.FastMoney;

/**
 * Converter to {@link MonetaryAmount} using the {@link FastMoney} implementation using {@link FastMoney#toString()}
 * and {@link FastMoney#parse(CharSequence)}
 * @author Otavio Santana
 * @author Werner Keil
 */
@Converter
public class FastMoneyConverter implements AttributeConverter<MonetaryAmount, String> {

	@Override
	public String convertToDatabaseColumn(MonetaryAmount attribute) {
		
		if (attribute == null) {
			return null;
		}
		return FastMoney.from(attribute).toString();
	}

	@Override
	public MonetaryAmount convertToEntityAttribute(String dbData){
		if (dbData == null) {
			return null;
		}
		return FastMoney.parse(dbData);
	}

}