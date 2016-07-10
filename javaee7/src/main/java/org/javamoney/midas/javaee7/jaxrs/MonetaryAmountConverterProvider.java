package org.javamoney.midas.javaee7.jaxrs;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.money.MonetaryAmount;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import org.javamoney.moneta.Money;

@Provider
public class MonetaryAmountConverterProvider implements ParamConverterProvider {


    @Override
    public <T> ParamConverter<T> getConverter(final Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (MonetaryAmount.class.isInstance(rawType)) {
            return new ParamConverter<T>() {
                @Override
                public T fromString(String value) {
                    if(value == null || value.isEmpty()) {
                        return null;
                    }
                    return rawType.cast(Money.parse(value));
                }

                @Override
                public String toString(T value) {
                    if(value == null) {
                        return null;
                    }
                    return value.toString();
                }
            };
        }
        return null;
    }
}
