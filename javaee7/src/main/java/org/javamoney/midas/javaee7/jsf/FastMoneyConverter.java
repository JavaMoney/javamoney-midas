/*
 * Copyright (c) 2015, 2016, Werner Keil and others by the @author tag.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.javamoney.midas.javaee7.jsf;

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
 * @author Werner Keil
 */
@FacesConverter("money.midas.FastMoneyConverter")
public class FastMoneyConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		if (value == null) {
			return null;
		}
		return FastMoney.parse(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			return null;
		}
		return value.toString(); // What about Objects.toString(value)?
	}
}