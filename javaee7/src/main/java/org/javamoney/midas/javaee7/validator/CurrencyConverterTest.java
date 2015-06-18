package org.javamoney.midas.javaee7.validator;

import javax.validation.ConstraintValidatorContext;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyConverterTest {


	private CurrencyUnitValidator currencyUnitValidator;
	
	@Mock
	private Currency currency;
	
	@Mock
	private ConstraintValidatorContext context;

	
	@Before
	public void setup() {
		currencyUnitValidator = new CurrencyUnitValidator();
	}
	
}
