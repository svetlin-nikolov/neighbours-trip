package com.example.demo.exchange;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.CountryDTO;
import com.example.demo.model.dto.CountryBudgetExchangeResultDTO;
import com.example.demo.providers.CurrencyExchangeRateProvider;

/**
 * Exchanger used to change the country budget from one country currency to another.
 */
@Component
public class CountryBudgetCurrencyExchanger {

	private final CurrencyExchangeRateProvider currencyProvider;

	@Autowired
	public CountryBudgetCurrencyExchanger(CurrencyExchangeRateProvider currencyProvider) {
		this.currencyProvider = currencyProvider;
	}

	/**
	 * Exchange the country budget.
	 * @param countryToExangeTo - the country currency to exchange to.
	 * @param countryBudget - the budget for the country in input currency.
	 * @param fromCurrecy - the current currency to be exchanged from.
	 * @return the exchanged country budget.
	 */
	public CountryBudgetExchangeResultDTO exchange(String fromCurrency, CountryDTO countryToExchangeTo, int countryBudget) {
		BigDecimal countryBudgetBigDecimal = new BigDecimal(countryBudget).setScale(2);
		BigDecimal exchangeRate = currencyProvider.getCurrencyExcangeRate(fromCurrency, countryToExchangeTo.getCurrency());
		if (fromCurrency.equals(countryToExchangeTo.getCurrency()) || exchangeRate.equals(BigDecimal.ZERO)) {
			return new CountryBudgetExchangeResultDTO(countryToExchangeTo.getIsoCode(), fromCurrency, countryBudgetBigDecimal);
		}
		BigDecimal conversionResult = countryBudgetBigDecimal.multiply(exchangeRate).setScale(2, RoundingMode.CEILING);
		return new CountryBudgetExchangeResultDTO(countryToExchangeTo.getIsoCode(), countryToExchangeTo.getCurrency(), conversionResult);
	}

}