package com.keyin.service;

import com.keyin.entity.Aircraft;
import com.keyin.repository.AircraftRepository;
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

public class AircraftServiceTest {

    @Mock
    private AircraftRepository aircraftRepository;

    @Mock
    private FlightRepository flightRepository;

    private AircraftService aircraftService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        aircraftService = new AircraftService(aircraftRepository, flightRepository);
    }

    @Test
    void getAllAircrafts() {
        Aircraft aircraft = new Aircraft();
        aircraft.setAircraftId("AC001");
        aircraft.setModel("Boeing 737");

        when(aircraftRepository.findAll()).thenReturn(Arrays.asList(aircraft));

        List<Aircraft> aircrafts = aircraftService.getAllAircrafts();

        assertNotNull(aircrafts);
        assertEquals(1, aircrafts.size());
        assertEquals("AC001", aircrafts.get(0).getAircraftId());
    }

    @Test
    void getAircraftByAircraftId() {
        Aircraft aircraft = new Aircraft();
        aircraft.setAircraftId("AC001");

        when(aircraftRepository.findByAircraftId("AC001")).thenReturn(Optional.of(aircraft));

        Aircraft result = aircraftService.getAircraftByAircraftId("AC001");

        assertNotNull(result);
        assertEquals("AC001", result.getAircraftId());
    }
}
