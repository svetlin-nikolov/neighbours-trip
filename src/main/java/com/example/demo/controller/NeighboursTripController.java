package com.example.demo.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.StartingCountry;
import com.example.demo.model.dto.TripCalculationResultDTO;
import com.example.demo.service.NeighboursTripService;

/**
 * Rest controller for neighbours trip.
 */
@RestController
@Validated
@RequestMapping(value = "neighbours-trip")
public class NeighboursTripController {
	
	private NeighboursTripService neighbourCountriesService;
	
	@Autowired
	public NeighboursTripController(NeighboursTripService neighbourCountriesTripService) {
		this.neighbourCountriesService = neighbourCountriesTripService;
	}
	
	/**
	 * Endpoint that returns the calculation of the neighbour trips based on the input.
	 * The request param validation is turned off here because we use the one from the @NotNull.
	 * This way if there is more than one error it will be shown.
	 * @param startingCountry - the country to start from.
	 * @param totalBudget - the total budget we have for the trip.
	 * @param countryBuget - the budget we have per country.
	 * @param currency - the currency of the budget.
	 * @return TripCalculationResult containing information about the trip.
	 */
	@GetMapping("/calculate/{startingCountry}")
	public TripCalculationResultDTO calculateTrips(@NotNull @PathVariable("startingCountry") StartingCountry startingCountry,
			@NotNull @RequestParam(required = false) @NotNull Integer totalBudget, @NotNull @RequestParam(required = false) Integer countryBudget, 
			@NotNull @Pattern(regexp = "^[A-Z]{3}") @RequestParam(required = false) String currency) {
		return neighbourCountriesService.calculateNeighbourTrips(startingCountry, totalBudget, countryBudget, currency);
	}
}