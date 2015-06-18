package org.javamoney.midas.javaee7.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CurrencyUnitValidator.class)
@Documented
public @interface Currency {

	String message() default "{org.javamoney.midas.constraints.currencyUnit}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    /**
     * Informs the currencies that will be allowed using the locale where the currency the currency code.
     * Ex: 'USD', 'BRL', 'EUR'
     * @return currencies allowed using the currency code
     */
    String[] acceptedCurrencies() default "";
    /**
     * Informs the currencies that will be allowed using the locale where the currency is from.
     * Ex: 'en_US','pt_BR', 'en_GB'
     * @return currencies allowed using the currency code
     */
    String[] acceptedCurrenciesFromLocales() default "";
    /**
     * Informs the currencies that will be rejected using the locale where the currency the currency code.
     * Ex: 'USD', 'BRL', 'EUR'
     * @return currencies allowed using the currency code
     */
    String[] rejectedCurrencies() default "";
    /**
     * Informs the currencies that will be rejected using the locale where the currency is from.
     * Ex: 'en_US','pt_BR', 'en_GB'
     * @return currencies allowed using the currency code
     */
    String[] rejectedCurrenciesFromLocales() default "";
}