package com.keyin.controller;

import com.keyin.entity.Aircraft;
import com.keyin.entity.Airport;
import com.keyin.entity.Flight;
import com.keyin.repository.AircraftRepository;
import com.keyin.repository.AirportRepository;
import com.keyin.repository.FlightRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/seed")
public class SeedController {

    private final AirportRepository airportRepository;
    private final AircraftRepository aircraftRepository;
    private final FlightRepository flightRepository;

    public SeedController(AirportRepository airportRepository, AircraftRepository aircraftRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.aircraftRepository = aircraftRepository;
        this.flightRepository = flightRepository;
    }

    @PostMapping
    public String seedData() {
        flightRepository.deleteAll();
        aircraftRepository.deleteAll();
        airportRepository.deleteAll();

        Airport stJohns = new Airport();
        stJohns.setName("St. John's International Airport");
        stJohns.setIataCode("YYT");
        stJohns.setLocation("St. John's, Newfoundland");
        airportRepository.save(stJohns);

        Airport gander = new Airport();
        gander.setName("Gander International Airport");
        gander.setIataCode("YQX");
        gander.setLocation("Gander, Newfoundland");
        airportRepository.save(gander);

        Airport deerLake = new Airport();
        deerLake.setName("Deer Lake Regional Airport");
        deerLake.setIataCode("YDF");
        deerLake.setLocation("Deer Lake, Newfoundland");
        airportRepository.save(deerLake);

        Aircraft aircraft1 = new Aircraft();
        aircraft1.setAircraftId("AC001");
        aircraft1.setModel("Boeing 737");
        aircraft1.setManufacturer("Boeing");
        aircraft1.setCapacity(160);
        aircraft1.setFlightRange(6000);
        aircraft1.setSpeed(850);
        aircraftRepository.save(aircraft1);

        Aircraft aircraft2 = new Aircraft();
        aircraft2.setAircraftId("AC002");
        aircraft2.setModel("Airbus A320");
        aircraft2.setManufacturer("Airbus");
        aircraft2.setCapacity(150);
        aircraft2.setFlightRange(5700);
        aircraft2.setSpeed(830);
        aircraftRepository.save(aircraft2);

        Flight flight1 = new Flight();
        flight1.setFlightNumber("FL001");
        flight1.setAirline("Air Canada");
        flight1.setDepartureAirport(findAirportByIataCode("YYT"));
        flight1.setArrivalAirport(findAirportByIataCode("YQX"));
        flight1.setScheduledDepartureTime(LocalDateTime.now().plusHours(2));
        flight1.setScheduledArrivalTime(LocalDateTime.now().plusHours(3));
        flight1.setStatus("Landed");
        flight1.setGate("A1");
        flight1.setTerminal("T1");
        flight1.setAircraft(aircraft1);
        flightRepository.save(flight1);

        Flight flight2 = new Flight();
        flight2.setFlightNumber("FL002");
        flight2.setAirline("WestJet");
        flight2.setDepartureAirport(findAirportByIataCode("YQX"));
        flight2.setArrivalAirport(findAirportByIataCode("YDF"));
        flight2.setScheduledDepartureTime(LocalDateTime.now().plusHours(4));
        flight2.setScheduledArrivalTime(LocalDateTime.now().plusHours(5));
        flight2.setStatus("On Time");
        flight2.setGate("B2");
        flight2.setTerminal("T2");
        flight2.setAircraft(aircraft2);
        flightRepository.save(flight2);

        Flight flight3 = new Flight();
        flight3.setFlightNumber("FL003");
        flight3.setAirline("PAL Airlines");
        flight3.setDepartureAirport(findAirportByIataCode("YDF"));
        flight3.setArrivalAirport(findAirportByIataCode("YYT"));
        flight3.setScheduledDepartureTime(LocalDateTime.now().plusHours(6));
        flight3.setScheduledArrivalTime(LocalDateTime.now().plusHours(7));
        flight3.setStatus("Delayed");
        flight3.setGate("C3");
        flight3.setTerminal("T3");
        flight3.setAircraft(aircraft1);
        flightRepository.save(flight3);

        return "Database seeded successfully with mock data!";
    }

    private Airport findAirportByIataCode(String iataCode) {
        return airportRepository.findByIataCode(iataCode)
                .orElseThrow(() -> new RuntimeException("Airport with IATA code " + iataCode + " not found"));
    }
}
