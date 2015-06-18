package org.javamoney.midas.javaee7.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Locale;

import javax.money.Monetary;

import org.hamcrest.Matchers;
import org.javamoney.midas.javaee7.validator.Currency;
import org.javamoney.midas.javaee7.validator.CurrencyConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyUnitValidatorTest {


	@Mock
	private Currency currency;

	@Before
	public void setup() {
		when(currency.acceptedCurrencies()).thenReturn(new String[0]);
		when(currency.rejectedCurrencies()).thenReturn(new String[0]);

		when(currency.acceptedCurrenciesFromLocales()).thenReturn(new String[0]);
		when(currency.rejectedCurrenciesFromLocales()).thenReturn(new String[0]);
	}

	@Test
	public void shouldReturnsEmptyAcceptedAndRejectedListWhenCurrencyCodeIsEmpty(){
		CurrencyConverter converter = new CurrencyConverter(currency);

		assertTrue(converter.getAcceptedCurrencies().isEmpty());
		assertTrue(converter.getRejectedCurrencies().isEmpty());
	}

	@Test
	public void shouldReturnsOneElementAcceptedAndRejectedListWhenCurrencyCodeHasOneElement(){
		when(currency.acceptedCurrencies()).thenReturn(new String[]{"BRL"});
		when(currency.rejectedCurrencies()).thenReturn(new String[]{"BRL"});

		CurrencyConverter converter = new CurrencyConverter(currency);
		assertFalse(converter.getAcceptedCurrencies().isEmpty());
		assertFalse(converter.getRejectedCurrencies().isEmpty());

		assertThat(converter.getAcceptedCurrencies().toArray(),
				Matchers.arrayContaining(Monetary.getCurrency("BRL")));
		assertThat(converter.getRejectedCurrencies().toArray(),
				Matchers.arrayContaining(Monetary.getCurrency("BRL")));
	}

	@Test
	public void shouldReturnsElementsAcceptedAndRejectedListWhenCurrencyCodeHasElements(){
		when(currency.acceptedCurrencies()).thenReturn(new String[]{"BRL","USD"});
		when(currency.rejectedCurrencies()).thenReturn(new String[]{"BRL","USD"});

		CurrencyConverter converter = new CurrencyConverter(currency);
		assertFalse(converter.getAcceptedCurrencies().isEmpty());
		assertFalse(converter.getRejectedCurrencies().isEmpty());

		assertThat(converter.getAcceptedCurrencies().toArray(),
				Matchers.arrayContaining(Monetary.getCurrency("BRL"), Monetary.getCurrency("USD")));

		assertThat(converter.getRejectedCurrencies().toArray(),
				Matchers.arrayContaining(Monetary.getCurrency("BRL"), Monetary.getCurrency("USD")));
	}


	@Test
	public void shouldReturnsEmptyAcceptedAndRejectedListWhenLocaleIsEmpty(){

		CurrencyConverter converter = new CurrencyConverter(currency);

		assertTrue(converter.getAcceptedCurrencies().isEmpty());
		assertTrue(converter.getRejectedCurrencies().isEmpty());
	}

	@Test
	public void shouldReturnsOneElementAcceptedAndRejectedListWhenLocaleHasOneElement(){

		when(currency.acceptedCurrenciesFromLocales()).thenReturn(new String[]{"en_US"});
		when(currency.rejectedCurrenciesFromLocales()).thenReturn(new String[]{"en_US"});

		CurrencyConverter converter = new CurrencyConverter(currency);
		assertFalse(converter.getAcceptedCurrencies().isEmpty());
		assertFalse(converter.getRejectedCurrencies().isEmpty());

		assertThat(converter.getAcceptedCurrencies().toArray(),
				Matchers.arrayContaining(Monetary.getCurrency(Locale.US)));
		assertThat(converter.getRejectedCurrencies().toArray(),
				Matchers.arrayContaining(Monetary.getCurrency(Locale.US)));
	}

	@Test
	public void shouldReturnsElementsAcceptedAndRejectedListWhenLocaleHasElements(){

		when(currency.acceptedCurrenciesFromLocales()).thenReturn(new String[]{"en_US","en_GB"});
		when(currency.rejectedCurrenciesFromLocales()).thenReturn(new String[]{"en_US","en_GB"});

		CurrencyConverter converter = new CurrencyConverter(currency);
		assertFalse(converter.getAcceptedCurrencies().isEmpty());
		assertFalse(converter.getRejectedCurrencies().isEmpty());

		assertThat(converter.getAcceptedCurrencies().toArray(),
				Matchers.arrayContaining(Monetary.getCurrency(Locale.US), Monetary.getCurrency(Locale.UK)));

		assertThat(converter.getRejectedCurrencies().toArray(),
				Matchers.arrayContaining(Monetary.getCurrency(Locale.US), Monetary.getCurrency(Locale.UK)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldReturnsErrorOnElementsAcceptedWhenLocaleIsWrong(){
		when(currency.acceptedCurrenciesFromLocales()).thenReturn(new String[]{"en"});
		new CurrencyConverter(currency);
	}

}
