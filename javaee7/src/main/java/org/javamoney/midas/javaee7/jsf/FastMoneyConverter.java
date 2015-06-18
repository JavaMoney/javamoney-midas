package org.javamoney.midas.javaee7.jsf;

import java.util.Objects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.FastMoney;

/**
 * Converter to {@link MonetaryAmount} using the {@link FastMoney} implementation using {@link FastMoney#toString()}
 * and {@link FastMoney#parse(CharSequence)}
 * @author Otavio Santana
 */
@FacesConverter("money.midas.FastMoneyConverter")
public class FastMoneyConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		if (Objects.isNull(value)) {
			return null;
		}
		return FastMoney.parse(value);
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