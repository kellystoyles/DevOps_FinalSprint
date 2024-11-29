package com.keyin.controller;

import com.keyin.entity.Flight;
import com.keyin.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{flightNumber}")
    public Flight getFlightByFlightNumber(@PathVariable String flightNumber) {
        return flightService.getFlightByFlightNumber(flightNumber);
    }

    @GetMapping("/status/{status}")
    public List<Flight> getFlightsByStatus(@PathVariable String status) {
        return flightService.getFlightsByStatus(status);
    }

    @GetMapping("/aircraft/{aircraftId}")
    public List<Flight> getFlightsByAircraftId(@PathVariable Long aircraftId) {
        return flightService.getFlightsByAircraftId(aircraftId);
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.saveFlight(flight);
    }

    @PutMapping("/{flightNumber}")
    public Flight updateFlight(@PathVariable String flightNumber, @RequestBody Flight updatedFlight) {
        return flightService.updateFlight(flightNumber, updatedFlight);
    }

    @DeleteMapping("/{flightNumber}")
    public void deleteFlight(@PathVariable String flightNumber) {
        flightService.deleteFlight(flightNumber);
    }
}


