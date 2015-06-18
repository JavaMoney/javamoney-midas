package org.javamoney.midas.javaee7.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.Set;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class CurrencyAcceptedTest {


	private static Validator validator;

	   @BeforeClass
	   public static void setUp() {
	      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	      validator = factory.getValidator();
	   }

	   @Test
	   public void shouldReturnsEmptyConstrainsWhenCurrencyIsNull(){
		   CurrencyValidator currency = new CurrencyValidator(null);
		   Set<ConstraintViolation<CurrencyValidator>> constraintViolations =
				      validator.validate(currency);
		   assertTrue(constraintViolations.isEmpty());
	   }

	   @Test
	   public void shouldReturnsEmptyConstrainsWhenCurrencyIsAllowedUsingCurrencyCode(){
		   CurrencyValidator currency = new CurrencyValidator(Monetary.getCurrency("BRL"));
		   Set<ConstraintViolation<CurrencyValidator>> constraintViolations =
				      validator.validate(currency);
		   assertTrue(constraintViolations.isEmpty());
	   }

	   @Test
	   public void shouldReturnsEmptyConstrainsWhenCurrencyIsAllowedUsingLocale(){
		   CurrencyValidator currency = new CurrencyValidator(Monetary.getCurrency(new Locale("pt", "BR")));
		   Set<ConstraintViolation<CurrencyValidator>> constraintViolations =
				      validator.validate(currency);
		   assertTrue(constraintViolations.isEmpty());
	   }

	   @Test
	   public void shouldReturnsConstrainsWhenCurrencyDenieddUsingLocale(){
		   CurrencyValidator currency = new CurrencyValidator(Monetary.getCurrency(Locale.US));
		   Set<ConstraintViolation<CurrencyValidator>> constraintViolations =
				      validator.validate(currency);

		   assertTrue(constraintViolations.size() == 1);
		   assertEquals("{org.javamoney.midas.constraints.currencyAccepted}", constraintViolations.iterator().next().getMessageTemplate());
	   }

	public class CurrencyValidator {

		@CurrencyAccepted(currencies = "BRL")
		private CurrencyUnit currencyUnit;

		CurrencyValidator(CurrencyUnit currencyUnit) {
			this.currencyUnit = currencyUnit;
		}

	}
}
