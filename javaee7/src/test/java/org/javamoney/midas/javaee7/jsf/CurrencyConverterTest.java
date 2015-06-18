package org.javamoney.midas.javaee7.jsf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.javamoney.midas.javaee7.jsf.CurrencyConverter;
import org.junit.Before;
import org.junit.Test;

public class CurrencyConverterTest {
	
	private CurrencyConverter converter;
	
	private FacesContext context;
	
	private UIComponent component;
	
	private final String currencyCode = "BRL";
	
	@Before
	public void init() {
		converter = new CurrencyConverter();
	}
	
	@Test
	public void shouldReturnsNullWhenStringIsNull() {
		Object result = converter.getAsObject(context, component, null);
		assertNull(result);
	}
	
	@Test
	public void shouldReturnsCurrencyWhenHasCurrencyCode() {
		Object result = converter.getAsObject(context, component, currencyCode);
		assertNotNull(result);
		CurrencyUnit currency = CurrencyUnit.class.cast(result);
		assertEquals(Monetary.getCurrency(currencyCode), currency);
	}
	
	
	@Test
	public void shouldReturnsNullWhenObjectIsNull() {
		Object result = converter.getAsString(context, component, null);
		assertNull(result);
	}
	
	@Test
	public void shouldReturnsCurrencyCodeWhenHasCurrency() {
		CurrencyUnit expectedCurrency = Monetary.getCurrency(currencyCode);
		String result = converter.getAsString(context, component, expectedCurrency);
		assertNotNull(result);
		assertEquals(Monetary.getCurrency(currencyCode).toString(), result.toString());
	}

}
