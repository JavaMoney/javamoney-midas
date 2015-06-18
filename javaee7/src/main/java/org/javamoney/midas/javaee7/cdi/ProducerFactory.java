package org.javamoney.midas.javaee7.cdi;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.money.MonetaryOperator;

import org.javamoney.moneta.spi.FastMoneyProducer;
import org.javamoney.moneta.spi.MonetaryAmountProducer;
import org.javamoney.moneta.spi.MoneyProducer;
import org.javamoney.moneta.spi.RoundedMoneyProducer;

class ProducerFactory {


	@Inject
	private MonetaryOperator rounding;

	@Produces
	@Default
	@MonetaryAmount
	public MonetaryAmountProducer getDefaultProducer(){
		return new MoneyProducer();
	}

	@Produces
	@MonetaryAmount(MonetaryAmountType.FAST_MONEY)
	public MonetaryAmountProducer getFastMoneyProducer(){
		return new FastMoneyProducer();
	}

	@Produces
	@MonetaryAmount(MonetaryAmountType.FAST_MONEY)
	public MonetaryAmountProducer getRoundedMoneyProducer(){
		return new RoundedMoneyProducer(rounding);
	}

}
