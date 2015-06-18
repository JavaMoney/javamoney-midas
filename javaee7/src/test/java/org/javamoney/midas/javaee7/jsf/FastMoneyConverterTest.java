package org.javamoney.midas.javaee7.jsf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.money.MonetaryAmount;

import org.javamoney.midas.javaee7.jsf.FastMoneyConverter;
import org.javamoney.moneta.FastMoney;
import org.junit.Before;
import org.junit.Test;

public class FastMoneyConverterTest {

private FastMoneyConverter converter;
	
	private FacesContext context;
	
	private UIComponent component;
	
	private final String monetaryValue = "BRL 10";
	
	@Before
	public void init() {
		converter = new FastMoneyConverter();
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
		FastMoney currency = FastMoney.class.cast(result);
		assertEquals(FastMoney.parse(monetaryValue), currency);
	}
	
	@Test
	public void shouldReturnsNullWhenObjectIsNull() {
		Object result = converter.getAsString(context, component, null);
		assertNull(result);
	}
	
	@Test
	public void shouldReturnsCurrencyCodeWhenHasCurrency() {
		MonetaryAmount expectedMoney = FastMoney.parse(monetaryValue);
		String result = converter.getAsString(context, component, expectedMoney);
		assertNotNull(result);
		assertEquals(expectedMoney.toString(), result);
	}
}
