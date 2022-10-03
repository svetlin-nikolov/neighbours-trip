package com.example.demo.model.dto;

import java.util.List;

/**
 * DTO used to store the calculations result for the countries trip.
 */
public class TripCalculationResultDTO {
	
	private final int neighbourCountriesTravelCount;
	private final int leftOver;
	private final String leftOverCurrency;
	private final List<CountryBudgetExchangeResultDTO> neighbourCountriesResult;
	
	public TripCalculationResultDTO(int neighbourCountriesTravelCount, int leftOver, String leftOverCurrency, List<CountryBudgetExchangeResultDTO> neighbourCountriesResult) {
		this.leftOver = leftOver;
		this.leftOverCurrency = leftOverCurrency;
		this.neighbourCountriesResult = neighbourCountriesResult;
		this.neighbourCountriesTravelCount = neighbourCountriesTravelCount;
	}

	public int getLeftOver() {
		return leftOver;
	}

	public String getLeftOverCurrency() {
		return leftOverCurrency;
	}

	public List<CountryBudgetExchangeResultDTO> getNeighbourCountriesResult() {
		return neighbourCountriesResult;
	}

	public int getNeighbourCountriesTravelCount() {
		return neighbourCountriesTravelCount;
	}
}
