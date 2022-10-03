package com.example.demo.model.dto;

/**
 * DTO used to store the country information.
 */
public class CountryDTO {
	
	private final String currency;
	private final String isoCode;
	
	public CountryDTO(String isoCode, String currency) {
		this.currency = currency;
		this.isoCode = isoCode;
	}

	public String getCurrency() {
		return currency;
	}

	public String getIsoCode() {
		return isoCode;
	}
}
