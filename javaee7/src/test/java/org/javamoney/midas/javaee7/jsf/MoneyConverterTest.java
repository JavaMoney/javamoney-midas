package org.javamoney.midas.javaee7.jsf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.money.MonetaryAmount;

import org.javamoney.midas.javaee7.jsf.MoneyConverter;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;

public class MoneyConverterTest {
	
	private MoneyConverter converter;
	
	private FacesContext context;
	
	private UIComponent component;
	
	private final String monetaryValue = "BRL 10";
	
	@Before
	public void init() {
		converter = new MoneyConverter();
	}
	
	@Test
	public void shouldReturnsNullWhenStringIsNull() {
		Object result = converter.getAsObject(context, component, null);
		assertNull(result);
	}
	
	@Test
	public void shouldReturnsCurrencyWhenHasCurrencyCode() {
		Object result = converter.getAsObject(context, component, monetaryValue);
		assertNotNull(result);
		Money currency = Money.class.cast(result);
		assertEquals(Money.parse(monetaryValue), currency);
	}
	
	@Test
	public void shouldReturnsNullWhenObjectIsNull() {
		Object result = converter.getAsString(context, component, null);
		assertNull(result);
	}
	
	@Test
	public void shouldReturnsCurrencyCodeWhenHasCurrency() {
		MonetaryAmount expectedMoney = Money.parse(monetaryValue);
		String result = converter.getAsString(context, component, expectedMoney);
		assertNotNull(result);
		assertEquals(expectedMoney.toString(), result);
	}
}
