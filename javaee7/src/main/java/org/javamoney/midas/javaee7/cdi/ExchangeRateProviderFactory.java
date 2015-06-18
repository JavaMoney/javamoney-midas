package org.javamoney.midas.javaee7.cdi;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.money.convert.ExchangeRateProvider;
import javax.money.convert.MonetaryConversions;

import org.javamoney.moneta.convert.ExchangeRateType;

public class ExchangeRateProviderFactory {

	@Produces
	@Default
	@ExchangeRateQualifier
	public ExchangeRateProvider getDefault(){
		return MonetaryConversions.getExchangeRateProvider(ExchangeRateType.ECB);
	}

	@Produces
	@ExchangeRateQualifier(ExchangeRateType.ECB_HIST)
	public ExchangeRateProvider getECBHistory(){
		return MonetaryConversions.getExchangeRateProvider(ExchangeRateType.ECB_HIST);
	}

	@Produces
	@ExchangeRateQualifier(ExchangeRateType.ECB_HIST90)
	public ExchangeRateProvider getECBHistory90(){
		return MonetaryConversions.getExchangeRateProvider(ExchangeRateType.ECB_HIST90);
	}

	@Produces
	@ExchangeRateQualifier(ExchangeRateType.IMF)
	public ExchangeRateProvider getIMF(){
		return MonetaryConversions.getExchangeRateProvider(ExchangeRateType.IMF);
	}

	@Produces
	@ExchangeRateQualifier(ExchangeRateType.IMF_HIST)
	public ExchangeRateProvider getIMFHistory(){
		return MonetaryConversions.getExchangeRateProvider(ExchangeRateType.IMF_HIST);
	}
}
