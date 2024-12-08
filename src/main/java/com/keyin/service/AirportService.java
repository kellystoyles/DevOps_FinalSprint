package com.keyin.service;

import com.keyin.entity.Airport;
import com.keyin.entity.Flight;
import com.keyin.repository.AirportRepository;
import com.keyin.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;

    public AirportService(AirportRepository airportRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportByIataCode(String iataCode) {
        return airportRepository.findByIataCode(iataCode)
                .orElseThrow(() -> new RuntimeException("Airport not found with IATA code: " + iataCode));
    }

    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport updateAirport(String iataCode, Airport updatedAirport) {
        Airport existingAirport = airportRepository.findByIataCode(iataCode)
                .orElseThrow(() -> new RuntimeException("Airport not found with IATA code: " + iataCode));

        existingAirport.setName(updatedAirport.getName());
        existingAirport.setLocation(updatedAirport.getLocation());
        return airportRepository.save(existingAirport);
    }

    @Transactional
    public void deleteAirport(String iataCode) {
        // Step 1: Find the airport by IATA code
        Optional<Airport> airportOptional = airportRepository.findByIataCode(iataCode);

        if (airportOptional.isEmpty()) {
            throw new RuntimeException("Airport not found with IATA code: " + iataCode);
        }

        Airport airport = airportOptional.get();

        // Step 2: Delete related flights
        // Delete flights that have this airport as the departure airport
        flightRepository.deleteByDepartureAirport_IataCode(iataCode);

        // Delete flights that have this airport as the arrival airport
        flightRepository.deleteByArrivalAirport_IataCode(iataCode);

        // Step 3: Delete the airport itself
        airportRepository.delete(airport);
    }

    public List<Flight> getDeparturesByIataCode(String iataCode) {
        return flightRepository.findByDepartureAirport_IataCode(iataCode);
    }

    public List<Flight> getArrivalsByIataCode(String iataCode) {
        return flightRepository.findByArrivalAirport_IataCode(iataCode);
    }


    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }
}
