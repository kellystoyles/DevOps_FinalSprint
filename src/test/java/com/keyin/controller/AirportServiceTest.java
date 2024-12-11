package com.keyin.service;

import com.keyin.entity.Airport;
import com.keyin.entity.Flight;
import com.keyin.repository.AirportRepository;
import com.keyin.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AirportServiceTest {

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private FlightRepository flightRepository;

    private AirportService airportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        airportService = new AirportService(airportRepository, flightRepository);
    }

    @Test
    void getAllAirports() {
        Airport airport = new Airport();
        airport.setName("Test Airport");
        airport.setIataCode("TST");

        when(airportRepository.findAll()).thenReturn(Arrays.asList(airport));

        List<Airport> airports = airportService.getAllAirports();

        assertNotNull(airports);
        assertEquals(1, airports.size());
        assertEquals("TST", airports.get(0).getIataCode());
    }

    @Test
    void getAirportByIataCode() {
        Airport airport = new Airport();
        airport.setIataCode("TST");

        when(airportRepository.findByIataCode("TST")).thenReturn(Optional.of(airport));

        Airport result = airportService.getAirportByIataCode("TST");

        assertNotNull(result);
        assertEquals("TST", result.getIataCode());
    }

    @Test
    void saveAirport() {
        Airport airport = new Airport();
        airport.setName("Test Airport");
        airport.setIataCode("TST");

        when(airportRepository.save(any(Airport.class))).thenReturn(airport);

        Airport savedAirport = airportService.saveAirport(airport);

        assertNotNull(savedAirport);
        assertEquals("TST", savedAirport.getIataCode());
    }

    @Test
    void deleteAirport() {
        String iataCode = "TST";
        Airport airport = new Airport();
        airport.setIataCode(iataCode);

        when(airportRepository.findByIataCode(iataCode)).thenReturn(Optional.of(airport));

        airportService.deleteAirport(iataCode);

        verify(flightRepository).deleteByDepartureAirport_IataCode(iataCode);
        verify(flightRepository).deleteByArrivalAirport_IataCode(iataCode);
        verify(airportRepository).delete(airport);
    }
}
