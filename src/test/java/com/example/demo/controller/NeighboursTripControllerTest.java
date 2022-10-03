package com.example.demo.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.StartingCountry;
import com.example.demo.service.NeighboursTripService;

@RunWith(MockitoJUnitRunner.class)
public class NeighboursTripControllerTest {
	private static final String CONTROLLER_PATH = "/neighbours-trip";
	@Mock
	private NeighboursTripService neighbourCountriesCalculateTripService;
	
	@InjectMocks
	private NeighboursTripController neighbourCountriesCalculateTripController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(neighbourCountriesCalculateTripController).build();
	}

	@Test
	public void calculateTrips_whenStartingCountryIsNotPresent_returnedStatusIsNotFound() throws Exception {
		 mockMvc.perform(get(CONTROLLER_PATH + "/calculate/?totalBudget=1200&countryBudget=200&currency=EU")
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isNotFound());
	}

	@Test
	public void calculateTrips_whenStartingCountryIsNotInTheList_returnedStatusIsBadRequest() throws Exception {
		 mockMvc.perform(get(CONTROLLER_PATH + "/calculate/BR?totalBudget=1200&countryBudget=200&currency=EU")
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isBadRequest());
	}

	@Test
	public void calculateTrips_whenAllValidationsPass_statusIsOk() throws Exception {
		 mockMvc.perform(get(CONTROLLER_PATH + "/calculate/BG?totalBudget=200&countryBudget=200&currency=EUR")
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isOk());
		 verify(neighbourCountriesCalculateTripService, times(1)).calculateNeighbourTrips(StartingCountry.BG, 200, 200, "EUR");
	}
}
