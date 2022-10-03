package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exchange.CountryBudgetCurrencyExchanger;
import com.example.demo.model.StartingCountry;
import com.example.demo.model.dto.TripCalculationResultDTO;
import com.example.demo.model.dto.CountryDTO;
import com.example.demo.model.dto.CountryBudgetExchangeResultDTO;
import com.example.demo.providers.NeighbourCountriesProvider;

/**
 * Service used for calculation of the neighbour trips based on the input.
 */
@Service
public class NeighboursTripService {

	private final CountryBudgetCurrencyExchanger countryBudgetCurrencyConverter;
	private final NeighbourCountriesProvider neighbourCountriesProvider;

	@Autowired
	public NeighboursTripService(NeighbourCountriesProvider neighbourCountriesProvider, CountryBudgetCurrencyExchanger currencyExchanger) {
		this.neighbourCountriesProvider = neighbourCountriesProvider;
		this.countryBudgetCurrencyConverter = currencyExchanger;
	}

	/**
	 * Calculate neighbour trips based on the input.
	 * @param startingCountry - the country to start from.
	 * @param totalBudget - the total budget we have for the trip.
	 * @param countryBuget - the budget we have per country.
	 * @param inputCurrency - the currency of the budget.
	 * @return TripCalculationResult containing information about the trip.
	 */
	public TripCalculationResultDTO calculateNeighbourTrips(StartingCountry startingCountry, int totalBudget, int countryBudget, String inputCurrency) {
		List<CountryDTO> neighbourCountries = neighbourCountriesProvider.getNeighbourCountries(startingCountry);
		int neededMoneyForOneTrip = countryBudget * neighbourCountries.size();
		int neighbourCountriesTravelCount = totalBudget / neededMoneyForOneTrip;
		int leftOverMoney = totalBudget % neededMoneyForOneTrip;
		List<CountryBudgetExchangeResultDTO> countryBudgetConversionResult = exchangeCountryBudgetToNeighbourCountryCurrencies(countryBudget, inputCurrency, neighbourCountries);
		return new TripCalculationResultDTO(neighbourCountriesTravelCount, leftOverMoney, inputCurrency, countryBudgetConversionResult);
	}

	private List<CountryBudgetExchangeResultDTO> exchangeCountryBudgetToNeighbourCountryCurrencies(int inputCountryBudget, String inputCurrency, List<CountryDTO> neighbourCountries) {
		return neighbourCountries.stream()
		.map(country -> countryBudgetCurrencyConverter.exchange(inputCurrency, country, inputCountryBudget))
		.collect(Collectors.toList());
	}

}
