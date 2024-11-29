package com.keyin.service;

import com.keyin.entity.Flight;
import com.keyin.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightByFlightNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new RuntimeException("Flight not found with number: " + flightNumber));
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight updateFlight(String flightNumber, Flight updatedFlight) {
        Flight existingFlight = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new RuntimeException("Flight not found with number: " + flightNumber));

        existingFlight.setAirline(updatedFlight.getAirline());
        existingFlight.setScheduledDepartureTime(updatedFlight.getScheduledDepartureTime());
        existingFlight.setScheduledArrivalTime(updatedFlight.getScheduledArrivalTime());
        existingFlight.setStatus(updatedFlight.getStatus());
        existingFlight.setGate(updatedFlight.getGate());
        existingFlight.setTerminal(updatedFlight.getTerminal());
        existingFlight.setAircraft(updatedFlight.getAircraft());
        return flightRepository.save(existingFlight);
    }

    public void deleteFlight(String flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new RuntimeException("Flight not found with number: " + flightNumber));
        flightRepository.delete(flight);
    }

    public List<Flight> getFlightsByStatus(String status) {
        return flightRepository.findByStatus(status);
    }

    public List<Flight> getFlightsByAircraftId(Long aircraftId) {
        return flightRepository.findByAircraft_Id(aircraftId);
    }
}
