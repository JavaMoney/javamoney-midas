package org.javamoney.midas.javaee7.validator;


import static java.util.Collections.binarySearch;
import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MonetaryAmountRejectedValidator implements ConstraintValidator<CurrencyAccepted, MonetaryAmount>{


	private final List<CurrencyUnit> currencies = new ArrayList<>();

	@Override
	public void initialize(CurrencyAccepted constraintAnnotation) {
		CurrencyReaderConverter reader = new CurrencyReaderConverter(constraintAnnotation);
		currencies.addAll(reader.getCurrencies());
		sort(currencies);
	}

	@Override
	public boolean isValid(MonetaryAmount value,
			ConstraintValidatorContext context) {
		if (Objects.isNull(value)) {
			return true;
		}
		return !containsCurrency(value.getCurrency());
	}

	private boolean containsCurrency(CurrencyUnit value) {
		return binarySearch(currencies, value) >= 0;
	}

	List<CurrencyUnit> getCurrencies() {
		return currencies;
	}

}
