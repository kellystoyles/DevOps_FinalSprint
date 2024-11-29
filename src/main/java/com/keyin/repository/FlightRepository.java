package com.keyin.repository;

import com.keyin.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findByFlightNumber(String flightNumber);
    List<Flight> findByAircraft_Id(Long aircraftId);
    List<Flight> findByDepartureAirport_IataCode(String iataCode);
    List<Flight> findByArrivalAirport_IataCode(String iataCode);
    List<Flight> findByStatus(String status);
}
