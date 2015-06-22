package org.javamoney.midas.javaee7.cdi;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.money.MonetaryOperator;

import org.javamoney.moneta.function.FastMoneyProducer;
import org.javamoney.moneta.function.MonetaryAmountProducer;
import org.javamoney.moneta.function.MoneyProducer;
import org.javamoney.moneta.function.RoundedMoneyProducer;


class ProducerFactory {


	@Inject
	private MonetaryOperator rounding;

	@Produces
	@Default
	@MonetaryAmountQualifier
	public MonetaryAmountProducer getDefaultProducer(){
		return new MoneyProducer();
	}

	@Produces
	@MonetaryAmountQualifier(MonetaryAmountType.FAST_MONEY)
	public MonetaryAmountProducer getFastMoneyProducer(){
		return new FastMoneyProducer();
	}

	@Produces
	@MonetaryAmountQualifier(MonetaryAmountType.FAST_MONEY)
	public MonetaryAmountProducer getRoundedMoneyProducer(){
		return new RoundedMoneyProducer(rounding);
	}

}
