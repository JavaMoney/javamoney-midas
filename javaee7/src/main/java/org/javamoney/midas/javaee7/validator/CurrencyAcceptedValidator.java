package org.javamoney.midas.javaee7.validator;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.money.CurrencyUnit;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyAcceptedValidator implements ConstraintValidator<CurrencyAccepted, CurrencyUnit>{


	private final List<CurrencyUnit> currencies = new ArrayList<>();

	@Override
	public void initialize(CurrencyAccepted constraintAnnotation) {
		CurrencyReaderConverter reader = new CurrencyReaderConverter(constraintAnnotation);
		currencies.addAll(reader.getCurrencies());
	}

	@Override
	public boolean isValid(CurrencyUnit value,
			ConstraintValidatorContext context) {
		if (Objects.isNull(value)) {
			return true;
		}
		return false;
	}

	List<CurrencyUnit> getCurrencies() {
		return currencies;
	}

}
