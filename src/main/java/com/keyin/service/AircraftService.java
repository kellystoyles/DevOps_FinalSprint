package com.keyin.service;

import com.keyin.entity.Aircraft;
import com.keyin.entity.Flight;
import com.keyin.repository.AircraftRepository;
import com.keyin.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftService {

    private final AircraftRepository aircraftRepository;
    private final FlightRepository flightRepository;

    public AircraftService(AircraftRepository aircraftRepository, FlightRepository flightRepository) {
        this.aircraftRepository = aircraftRepository;
        this.flightRepository = flightRepository;
    }

    public List<Aircraft> getAllAircrafts() {
        return aircraftRepository.findAll();
    }

    public Aircraft getAircraftByAircraftId(String aircraftId) {
        return aircraftRepository.findByAircraftId(aircraftId)
                .orElseThrow(() -> new RuntimeException("Aircraft not found with ID: " + aircraftId));
    }

    public List<Aircraft> getAircraftByModel(String model) {
        return aircraftRepository.findByModel(model);
    }

    public Aircraft saveAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    public Aircraft updateAircraft(Long id, Aircraft updatedAircraft) {
        Aircraft existingAircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aircraft not found with ID: " + id));

        existingAircraft.setModel(updatedAircraft.getModel());
        existingAircraft.setManufacturer(updatedAircraft.getManufacturer());
        existingAircraft.setCapacity(updatedAircraft.getCapacity());
        existingAircraft.setFlightRange(updatedAircraft.getFlightRange());
        existingAircraft.setSpeed(updatedAircraft.getSpeed());
        return aircraftRepository.save(existingAircraft);
    }

    @Transactional
    public void deleteAircraft(Long id) {
        // Step 1: Find the aircraft by ID
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aircraft not found with ID: " + id));

        // Step 2: Delete related flights
        // Delete flights that are associated with this aircraft
        flightRepository.deleteByAircraft_Id(id);

        // Step 3: Delete the aircraft itself
        aircraftRepository.delete(aircraft);
    }

    public List<Flight> getFlightsByAircraftId(Long aircraftId) {
        return flightRepository.findByAircraft_Id(aircraftId);
    }

}
