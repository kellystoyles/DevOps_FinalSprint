package com.keyin.controller;

import com.keyin.entity.Aircraft;
import com.keyin.entity.Airport;
import com.keyin.entity.Flight;
import com.keyin.service.AircraftService;
import com.keyin.service.AirportService;
import com.keyin.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class FlightControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FlightService flightService;
    @Mock
    private AirportService airportService;
    @Mock
    private AircraftService aircraftService;

    private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        flightController = new FlightController(flightService, airportService, aircraftService);
        mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
    }

    @Test
    void getAllFlights() throws Exception {
        Flight flight = new Flight();
        flight.setFlightNumber("FL001");

        when(flightService.getAllFlights()).thenReturn(Arrays.asList(flight));

        mockMvc.perform(get("/api/flights"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getFlightByNumber() throws Exception {
        Flight flight = new Flight();
        flight.setFlightNumber("FL001");

        when(flightService.getFlightByFlightNumber("FL001")).thenReturn(flight);

        mockMvc.perform(get("/api/flights/FL001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightNumber").value("FL001"));
    }

    @Test
    void createFlight() throws Exception {
        Map<String, Object> flightData = new HashMap<>();
        flightData.put("flightNumber", "FL001");
        flightData.put("airline", "Test Airline");
        flightData.put("status", "On Time");

        Flight flight = new Flight();
        flight.setFlightNumber("FL001");

        when(flightService.saveFlight(any(Flight.class))).thenReturn(flight);

        mockMvc.perform(post("/api/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"flightNumber\":\"FL001\",\"airline\":\"Test Airline\",\"status\":\"On Time\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteFlight() throws Exception {
        doNothing().when(flightService).deleteFlight("FL001");

        mockMvc.perform(delete("/api/flights/FL001"))
                .andExpect(status().isOk());

        verify(flightService, times(1)).deleteFlight("FL001");
    }
}
