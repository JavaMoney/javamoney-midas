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
@Constraint(validatedBy = {CurrencyAcceptedValidator.class, MonetaryAmountAcceptedValidator.class})
@Documented
public @interface CurrencyAccepted {

	String message() default "{org.javamoney.midas.constraints.currencyAccepted}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Informs the currency code.
     * Ex: 'USD', 'BRL', 'EUR'
     * @return currencies allowed using the currency code
     */
    String[] currencies() default "";
    /**
     * Informs the Locale where the currencies are from.
     * Ex: 'en_US','pt_BR', 'en_GB'
     * @return currencies allowed using the currency code
     */
    String[] currenciesFromLocales() default "";
}