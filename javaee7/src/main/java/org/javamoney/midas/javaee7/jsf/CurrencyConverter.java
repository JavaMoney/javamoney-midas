package org.javamoney.midas.javaee7.jsf;

import java.util.Objects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.money.CurrencyUnit;
import javax.money.Monetary;


/**
 * Converter to {@link CurrencyUnit} it serializes to {@link String} using the currency code.
 * @author Otavio Santana
 */
@FacesConverter("money.midas.CurrencyConverter")
public class CurrencyConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		if (Objects.isNull(value)) {
			return null;
		}
		return Monetary.getCurrency(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (Objects.isNull(value)) {
			return null;
		}
		return value.toString();
	}

}
