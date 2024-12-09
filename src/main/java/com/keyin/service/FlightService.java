package com.keyin.service;

import com.keyin.entity.Airport;
import com.keyin.entity.Flight;
import com.keyin.repository.AircraftRepository;
import com.keyin.repository.AirportRepository;
import com.keyin.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private AirportRepository airportRepository;

    public FlightService(FlightRepository flightRepository, AircraftRepository aircraftRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
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

    @Transactional
    public void deleteFlight(String flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new RuntimeException("Flight not found with number: " + flightNumber));

        // Handle bidirectional relationships
        if (flight.getDepartureAirport() != null) {
            flight.getDepartureAirport().getDepartures().remove(flight);
        }
        if (flight.getArrivalAirport() != null) {
            flight.getArrivalAirport().getArrivals().remove(flight);
        }
        if (flight.getAircraft() != null) {
            flight.getAircraft().getFlights().remove(flight);
        }

        flightRepository.delete(flight);
    }

    public List<Flight> getFlightsByStatus(String status) {
        return flightRepository.findByStatus(status);
    }

    public List<Flight> getFlightsByAircraftId(Long aircraftId) {
        return flightRepository.findByAircraft_Id(aircraftId);
    }

    public Flight createFlightWithIata(String flightNumber, String airline, String departureIata, String arrivalIata) {
        Airport departureAirport = airportRepository.findByIataCode(departureIata)
                .orElseThrow(() -> new RuntimeException("Departure airport not found"));
        Airport arrivalAirport = airportRepository.findByIataCode(arrivalIata)
                .orElseThrow(() -> new RuntimeException("Arrival airport not found"));

        Flight flight = new Flight();
        flight.setFlightNumber(flightNumber);
        flight.setAirline(airline);
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);

        return flightRepository.save(flight);
    }

}
