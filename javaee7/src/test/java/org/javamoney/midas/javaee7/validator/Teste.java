package org.javamoney.midas.javaee7.validator;

import org.javamoney.midas.javaee7.validator.Currency;

public class Teste {

	@Currency(acceptedCurrencies = {"", ""})
	private String a;
}

