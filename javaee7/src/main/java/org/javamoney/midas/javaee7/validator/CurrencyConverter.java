package org.javamoney.midas.javaee7.validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

class CurrencyConverter {

	private final List<CurrencyUnit> acceptedCurrencies = new ArrayList<>();
	
	private final List<CurrencyUnit> rejectedCurrencies = new ArrayList<>();
	
	public CurrencyConverter(Currency currency) {
		
		acceptedCurrencies.addAll(createCurrencyList(currency.acceptedCurrencies()));
		acceptedCurrencies.addAll(createCurrencyListFromLocale(currency.acceptedCurrenciesFromLocales()));
		
		rejectedCurrencies.addAll(createCurrencyList(currency.rejectedCurrencies()));
		rejectedCurrencies.addAll(createCurrencyListFromLocale(currency.rejectedCurrenciesFromLocales()));
	}
	
	
	private List<CurrencyUnit> createCurrencyList(String[] currenciesTexts) {
		if (currenciesTexts.length == 0) {
			return Collections.emptyList();
		}
		List<CurrencyUnit> currencies = new ArrayList<>();
		for (String value : currenciesTexts) {
			currencies.add(Monetary.getCurrency(value.trim()));
		}
		return currencies;
	}
	
	private List<CurrencyUnit> createCurrencyListFromLocale(
			String[] currenciesTexts) {
		
		if (currenciesTexts.length == 0) {
			return Collections.emptyList();
		}
		List<CurrencyUnit> currencies = new ArrayList<>();
		for (String value : currenciesTexts) {
			String[] aux = value.split("_");
			if (aux.length == 2) {
				currencies
						.add(Monetary.getCurrency(new Locale(aux[0], aux[1])));
			} else {
				throw new IllegalArgumentException(
						"On error happened on parameter: " + value);
			}
		}
		return currencies;
	}

	List<CurrencyUnit> getAcceptedCurrencies() {
		return acceptedCurrencies;
	}

	List<CurrencyUnit> getRejectedCurrencies() {
		return rejectedCurrencies;
	}
	
}
