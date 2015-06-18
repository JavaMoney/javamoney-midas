package org.javamoney.midas.javaee7.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import javax.money.Monetary;
import javax.validation.ConstraintValidatorContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyAcceptedValidatorTest {


	private CurrencyAcceptedValidator validator;

	@Mock
	private CurrencyAccepted constraintAnnotation;

	private ConstraintValidatorContext context;

	@Before
	public void setup() {
		when(constraintAnnotation.currencies()).thenReturn(new String[0]);
		when(constraintAnnotation.currenciesFromLocales()).thenReturn(new String[0]);
		validator = new CurrencyAcceptedValidator();
	}


	@Test
	public void shouldReturnsTrueWhenCurrecyIsNull() {
		Assert.assertTrue(validator.isValid(null, context));
	}

	@Test
	public void shouldReturnsTrueWhenCurrencyIsAllowed() {
		String currencyCodAllowed = "USD";
		when(constraintAnnotation.currencies()).thenReturn(new String[]{currencyCodAllowed});
		validator.initialize(constraintAnnotation);
		assertTrue(validator.isValid(Monetary.getCurrency(currencyCodAllowed), context));
	}

	@Test
	public void shouldReturnsFalseWhenCurrencyIsDenied() {
		String currencyCodAllowed = "USD";
		when(constraintAnnotation.currencies()).thenReturn(new String[]{currencyCodAllowed});
		validator.initialize(constraintAnnotation);
		assertFalse(validator.isValid(Monetary.getCurrency("EUR"), context));
	}
}
