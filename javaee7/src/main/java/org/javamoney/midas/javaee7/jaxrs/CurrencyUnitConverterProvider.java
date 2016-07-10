package org.javamoney.midas.javaee7.jaxrs;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;


@Provider
public class CurrencyUnitConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(final Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (CurrencyUnit.class.isInstance(rawType)) {
            return new ParamConverter<T>() {
                @Override
                public T fromString(String value) {
                    if(value == null || value.isEmpty()) {
                        return null;
                    }
                    return rawType.cast(Monetary.getCurrency(value));
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
