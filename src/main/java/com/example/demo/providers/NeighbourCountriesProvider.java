package com.example.demo.providers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.demo.model.StartingCountry;
import com.example.demo.model.dto.CountryDTO;

/**
 * Provider that returns a country's neighbours.
 */
@Component
public class NeighbourCountriesProvider {

	//mock data instead of using external rest api for simplicity
	private final Map<String, List<CountryDTO>> startingCountries;
	
	{
		CountryDTO turkey = new CountryDTO("TR", "TRY");
		CountryDTO greece = new CountryDTO("GR", "EUR");
		CountryDTO romania = new CountryDTO("RO", "RON");
		CountryDTO serbia = new CountryDTO("SR", "RSD");
		CountryDTO makedonia = new CountryDTO("MK", "MKD");
		List<CountryDTO> bulgariaNeighbourCountries = new ArrayList<>();
		bulgariaNeighbourCountries.add(turkey);
		bulgariaNeighbourCountries.add(greece);
		bulgariaNeighbourCountries.add(romania);
		bulgariaNeighbourCountries.add(serbia);
		bulgariaNeighbourCountries.add(makedonia);
		startingCountries = new HashMap<>();
		startingCountries.put("BG", bulgariaNeighbourCountries);
	}
	
	/**
	 * Retrieve neighbours by country.
	 * @param startingCountry - the country to start from.
	 * @return List<CountryDTO> containing all of the neighbour countries.
	 */
	public List<CountryDTO> getNeighbourCountries(StartingCountry startingCountry) {
		return startingCountries.get(startingCountry.toString());
	}
	
}
