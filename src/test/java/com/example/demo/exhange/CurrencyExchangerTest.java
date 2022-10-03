package com.example.demo.exhange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.exchange.CountryBudgetCurrencyExchanger;
import com.example.demo.model.dto.CountryDTO;
import com.example.demo.model.dto.CountryBudgetExchangeResultDTO;
import com.example.demo.providers.CurrencyExchangeRateProvider;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyExchangerTest {
	
	@Mock
	private CurrencyExchangeRateProvider currencyProvider;
	
	@InjectMocks
	private CountryBudgetCurrencyExchanger currencyExchanger;

	@Test
	public void exchange_whenThereIsNoEchangeRate_TheFromCurrencyIsReturned() {
		when(currencyProvider.getCurrencyExcangeRate("EUR", "CHF"))
		.thenReturn(BigDecimal.ZERO);
		CountryDTO country = new CountryDTO("CH", "CHF");

		CountryBudgetExchangeResultDTO result = currencyExchanger.exchange("EUR", country, 100);

		assertEquals(country.getIsoCode(), result.getCountryIsoCode());
		assertNotEquals(country.getCurrency(), result.getCurrency());
		assertEquals("EUR", result.getCurrency());
	}

	@Test
	public void exchange_whenFromCurrencyEqualsToCurrency_TheFromCurrencyIsReturned() {
		CountryDTO country = new CountryDTO("GR", "EUR");

		CountryBudgetExchangeResultDTO result = currencyExchanger.exchange("EUR", country, 100);

		assertEquals(country.getIsoCode(), result.getCountryIsoCode());
		assertEquals("EUR", result.getCurrency());
	}

	@Test
	public void exchange_whenThereIsExchangeRate_itIsExchanged() {
		String eurToTRYExchangeRate = "7.43116";
		when(currencyProvider.getCurrencyExcangeRate("EUR", "TRY"))
		.thenReturn(new BigDecimal(eurToTRYExchangeRate));
		CountryDTO country = new CountryDTO("TR", "TRY");

		CountryBudgetExchangeResultDTO result = currencyExchanger.exchange("EUR", country, 100);

		assertEquals(country.getIsoCode(), result.getCountryIsoCode());
		assertEquals("TRY", result.getCurrency());
		assertEquals(new BigDecimal("743.12"), result.getAmount());
	}

}
