package org.javamoney.midas.javaee7.jpa;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.midas.javaee7.jpa.MoneyConverter;
import org.javamoney.moneta.Money;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoneyConverterTest {

	private MoneyConverter converter;

	private CurrencyUnit currency;
	
	private MonetaryAmount expectedMoney;
	
	@Before
	public void setup() {
		converter = new MoneyConverter();
		currency = Monetary.getCurrency("USD");
		expectedMoney = Money.of(10, currency);
	}
	
	@Test
	public void shouldReturnsNullWhenAttributeIsNull() {
		String convertToDatabaseColumn = converter.convertToDatabaseColumn(null);
		assertNull(convertToDatabaseColumn);
	}
	
	@Test
	public void shouldReturnsValueWhenHasAttribute() {
		String convertToDatabaseColumn = converter.convertToDatabaseColumn(expectedMoney);
		assertEquals(expectedMoney.toString(), convertToDatabaseColumn);
	}
	
	@Test
	public void shouldReturnsNullWhenDataBaseAttributeIsNull() {
		String result = converter.convertToDatabaseColumn(null);
		assertNull(result);
	}
	
	@Test
	public void shouldReturnsCurrencyCodeWhenHasInformationFromDataBase() {
		MonetaryAmount moneyConverted = converter.convertToEntityAttribute(expectedMoney.toString());
		Assert.assertTrue(Money.class.isInstance(moneyConverted));
		assertEquals(expectedMoney, moneyConverted);
	}
	
	
}
