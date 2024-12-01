package com.keyin.service;

import com.keyin.entity.Airport;
import com.keyin.entity.Flight;
import com.keyin.repository.AirportRepository;
import com.keyin.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteAirport(String iataCode) {
        Airport airport = airportRepository.findByIataCode(iataCode)
                .orElseThrow(() -> new RuntimeException("Airport not found with IATA code: " + iataCode));
        airportRepository.delete(airport);
    }

    public List<Flight> getDeparturesByIataCode(String iataCode) {
        return flightRepository.findByDepartureAirport_IataCode(iataCode);
    }

    public List<Flight> getArrivalsByIataCode(String iataCode) {
        return flightRepository.findByArrivalAirport_IataCode(iataCode);
    }


}
