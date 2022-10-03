package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.exchange.CountryBudgetCurrencyExchanger;
import com.example.demo.model.StartingCountry;
import com.example.demo.model.dto.TripCalculationResultDTO;
import com.example.demo.model.dto.CountryDTO;
import com.example.demo.providers.NeighbourCountriesProvider;

@RunWith(MockitoJUnitRunner.class)
public class NeighboursTripServiceTest {

	@Mock
	private CountryBudgetCurrencyExchanger currencyExchanger;

	@Mock
	private NeighbourCountriesProvider neighbourCountriesProvider;

	@InjectMocks
	private NeighboursTripService neighbourCountriesCalculateTripService;

	@Test
	public void calculateNeighbourTrips_whenThereAreCountriesWithCorrectData_ThereIsCorrectResult() {
		CountryDTO tr = new CountryDTO("TR", "TRY");
		CountryDTO gr = new CountryDTO("GR", "EUR");
		CountryDTO ro = new CountryDTO("RO", "RON");
		CountryDTO sr = new CountryDTO("SR", "RSD");
		CountryDTO mk = new CountryDTO("MK", "MKD");
		List<CountryDTO> neighbourCountries = Arrays.asList(tr, gr, ro, sr, mk);
		when(neighbourCountriesProvider.getNeighbourCountries(StartingCountry.BG)).thenReturn(neighbourCountries);

		TripCalculationResultDTO result = neighbourCountriesCalculateTripService.calculateNeighbourTrips(StartingCountry.BG, 1200, 100, "EUR");

		assertEquals(2, result.getNeighbourCountriesTravelCount());
		assertEquals(200, result.getLeftOver());
		assertEquals("EUR", result.getLeftOverCurrency());
		assertEquals(5, result.getNeighbourCountriesResult().size());
	}
}
