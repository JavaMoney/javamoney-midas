package org.javamoney.midas.javaee7.cdi;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.servlet.http.HttpServletRequest;

class CurrencyProducer {

	@Inject
	private HttpServletRequest request;

	@Produces
	@Default
	public CurrencyUnit getDefault() {
		return Monetary.getCurrency(request.getLocale());
	}


}
