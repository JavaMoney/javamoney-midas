package org.javamoney.midas.javaee7.validator;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyUnitValidator implements ConstraintValidator<Currency, CurrencyUnit>{


	private final List<CurrencyUnit> acceptedCurrencies = new ArrayList<>();
	
	private final List<CurrencyUnit> rejectedCurrencies = new ArrayList<>();
	
	
	@Override
	public void initialize(Currency constraintAnnotation) {
		acceptedCurrencies.addAll(createCurrencyList(constraintAnnotation.acceptedCurrencies()));
		rejectedCurrencies.addAll(createCurrencyList(constraintAnnotation.rejectedCurrencies()));
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
	
	@Override
	public boolean isValid(CurrencyUnit value,
			ConstraintValidatorContext context) {
		if (Objects.isNull(value)) {
			return true;
		}
		return false;
	}

	List<CurrencyUnit> getAcceptedCurrencies() {
		return acceptedCurrencies;
	}

	List<CurrencyUnit> getRejectedCurrencies() {
		return rejectedCurrencies;
	}
	
	

}
