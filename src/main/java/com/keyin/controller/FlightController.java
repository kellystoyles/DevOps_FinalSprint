package com.keyin.controller;

import com.keyin.entity.Aircraft;
import com.keyin.entity.Airport;
import com.keyin.entity.Flight;
import com.keyin.service.AircraftService;
import com.keyin.service.AirportService;
import com.keyin.service.FlightService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;
    private final AirportService airportService;
    private final AircraftService aircraftService;

    public FlightController(FlightService flightService, AirportService airportService, AircraftService aircraftService) {
        this.flightService = flightService;
        this.airportService = airportService;
        this.aircraftService = aircraftService;
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
    public Flight createFlight(@RequestBody Map<String, Object> flightData) {
        Flight flight = new Flight();

        if (flightData.containsKey("flightNumber")) {
            flight.setFlightNumber((String) flightData.get("flightNumber"));
        }

        if (flightData.containsKey("airline")) {
            flight.setAirline((String) flightData.get("airline"));
        }

        if (flightData.containsKey("departureIata")) {
            String departureIata = (String) flightData.get("departureIata");
            Airport departureAirport = airportService.getAirportByIataCode(departureIata);
            flight.setDepartureAirport(departureAirport);
        }

        if (flightData.containsKey("arrivalIata")) {
            String arrivalIata = (String) flightData.get("arrivalIata");
            Airport arrivalAirport = airportService.getAirportByIataCode(arrivalIata);
            flight.setArrivalAirport(arrivalAirport);
        }

        if (flightData.containsKey("status")) {
            flight.setStatus((String) flightData.get("status"));
        }

        if (flightData.containsKey("scheduledDepartureTime")) {
            flight.setScheduledDepartureTime(LocalDateTime.parse((String) flightData.get("scheduledDepartureTime")));
        }

        if (flightData.containsKey("scheduledArrivalTime")) {
            flight.setScheduledArrivalTime(LocalDateTime.parse((String) flightData.get("scheduledArrivalTime")));
        }

        if (flightData.containsKey("gate")) {
            flight.setGate((String) flightData.get("gate"));
        }

        if (flightData.containsKey("terminal")) {
            flight.setTerminal((String) flightData.get("terminal"));
        }

        if (flightData.containsKey("aircraftId")) {
            String aircraftId = (String) flightData.get("aircraftId");
            Aircraft aircraft = aircraftService.getAircraftByAircraftId(aircraftId);
            flight.setAircraft(aircraft);
        }

        return flightService.saveFlight(flight);
    }

    @PutMapping("/{flightNumber}")
    public Flight updateFlight(@PathVariable String flightNumber, @RequestBody Map<String, Object> flightData) {
        Flight existingFlight = flightService.getFlightByFlightNumber(flightNumber);
        if (existingFlight == null) {
            throw new RuntimeException("Flight with number " + flightNumber + " not found");
        }

        if (flightData.containsKey("airline")) {
            existingFlight.setAirline((String) flightData.get("airline"));
        }

        if (flightData.containsKey("departureIata")) {
            String departureIata = (String) flightData.get("departureIata");
            Airport departureAirport = airportService.getAirportByIataCode(departureIata);
            existingFlight.setDepartureAirport(departureAirport);
        }

        if (flightData.containsKey("arrivalIata")) {
            String arrivalIata = (String) flightData.get("arrivalIata");
            Airport arrivalAirport = airportService.getAirportByIataCode(arrivalIata);
            existingFlight.setArrivalAirport(arrivalAirport);
        }

        if (flightData.containsKey("status")) {
            existingFlight.setStatus((String) flightData.get("status"));
        }

        if (flightData.containsKey("scheduledDepartureTime")) {
            existingFlight.setScheduledDepartureTime(LocalDateTime.parse((String) flightData.get("scheduledDepartureTime")));
        }

        if (flightData.containsKey("scheduledArrivalTime")) {
            existingFlight.setScheduledArrivalTime(LocalDateTime.parse((String) flightData.get("scheduledArrivalTime")));
        }

        if (flightData.containsKey("gate")) {
            existingFlight.setGate((String) flightData.get("gate"));
        }

        if (flightData.containsKey("terminal")) {
            existingFlight.setTerminal((String) flightData.get("terminal"));
        }

        if (flightData.containsKey("aircraftId")) {
            String aircraftId = (String) flightData.get("aircraftId");
            Aircraft aircraft = aircraftService.getAircraftByAircraftId(aircraftId);
            existingFlight.setAircraft(aircraft);
        }

        return flightService.saveFlight(existingFlight);
    }




    @DeleteMapping("/{flightNumber}")
    public void deleteFlight(@PathVariable String flightNumber) {
        flightService.deleteFlight(flightNumber);
    }


}


