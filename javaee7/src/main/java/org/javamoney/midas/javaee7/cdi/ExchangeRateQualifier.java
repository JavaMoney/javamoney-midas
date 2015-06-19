package org.javamoney.midas.javaee7.cdi;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import org.javamoney.moneta.convert.ExchangeRateType;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Qualifier
public @interface ExchangeRateQualifier {
	ExchangeRateType value() default ExchangeRateType.ECB;
}
