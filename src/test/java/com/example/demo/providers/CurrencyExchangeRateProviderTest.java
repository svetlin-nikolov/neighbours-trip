package com.example.demo.providers;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyExchangeRateProviderTest {
	
	@InjectMocks
	private CurrencyExchangeRateProvider currencyProvider;

	@Test
	public void getCurrencyExchangeRate_whenThereIsNoFromCurrencyPresent_thereIsNoResult() {
		BigDecimal result = currencyProvider.getCurrencyExcangeRate("TRY", "BGN");

		assertEquals(BigDecimal.ZERO, result);
	}

	@Test
	public void getCurrencyExchangeRate_whenThereIsNoToCurrencyPresent_thereIsNoResult() {
		BigDecimal result = currencyProvider.getCurrencyExcangeRate("EUR", "CHF");

		assertEquals(BigDecimal.ZERO, result);
	}

	@Test
	public void getCurrencyExchangeRate_whenThereIsFromAndToCurrenciesPresent_thereIsResult() {
		String eurToMkdExchangeRate = "61.60200";

		BigDecimal result = currencyProvider.getCurrencyExcangeRate("EUR", "MKD");

		assertEquals(new BigDecimal(eurToMkdExchangeRate), result);
	}

}
