package org.javamoney.midas.javaee7.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.javamoney.midas.javaee7.jpa.CurrencyConverter;
import org.junit.Before;
import org.junit.Test;

public class CurrencyConverterTest {
	
	private CurrencyConverter currencyConverter;
	
	private CurrencyUnit currencyUnit = Monetary.getCurrency("BRL");
	
	@Before
	public void setup() {
		currencyConverter = new CurrencyConverter();
	}
	
	@Test
	public void shouldReturnsNullWhenTheAttributeIsNull() {
		String result = currencyConverter.convertToDatabaseColumn(null);
		assertNull(result);
	}
	
	@Test
	public void shouldReturnsTheCurrenyCodeWhenHasAttribute() {
		
		String result = currencyConverter.convertToDatabaseColumn(currencyUnit);
		assertEquals(currencyUnit.toString(), result);
	}
	
	@Test
	public void shouldReturnsNullWhenDataBaseAttributeIsNull() {
		String result = currencyConverter.convertToDatabaseColumn(null);
		assertNull(result);
	}
	
	@Test
	public void shouldReturnsCurrencyCodeWhenHasInformationFromDataBase() {
		CurrencyUnit currencyConverted = currencyConverter.convertToEntityAttribute(currencyUnit.toString());
		assertEquals(currencyUnit, currencyConverted);
	}


}
