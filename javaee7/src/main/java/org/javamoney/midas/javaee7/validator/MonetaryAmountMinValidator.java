package org.javamoney.midas.javaee7.validator;


import java.math.BigDecimal;
import java.util.Objects;

import javax.money.MonetaryAmount;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MonetaryAmountMinValidator implements ConstraintValidator<MonetaryMin, MonetaryAmount>{



	private BigDecimal number;

	@Override
	public void initialize(MonetaryMin constraintAnnotation) {
		number = new BigDecimal(constraintAnnotation.value());
	}

	@Override
	public boolean isValid(MonetaryAmount value,
			ConstraintValidatorContext context) {
		if (Objects.isNull(value)) {
			return true;
		}
		return value.isGreaterThanOrEqualTo(value.getFactory().setNumber(number).create());
	}

}
