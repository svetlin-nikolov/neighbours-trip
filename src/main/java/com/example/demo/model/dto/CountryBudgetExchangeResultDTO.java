package com.example.demo.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * DTO used to store the converted country budget information.
 */
public class CountryBudgetExchangeResultDTO {
	
	private final String countryIsoCode;
	private final String currency;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private final BigDecimal amount;

	public CountryBudgetExchangeResultDTO(String countryIsoCode, String currency, BigDecimal amount) {
		this.countryIsoCode = countryIsoCode;
		this.currency = currency;
		this.amount = amount;
	}

	public String getCountryIsoCode() {
		return countryIsoCode;
	}

	public String getCurrency() {
		return currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

}
