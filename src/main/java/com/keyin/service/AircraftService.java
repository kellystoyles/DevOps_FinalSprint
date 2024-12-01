package com.keyin.service;

import com.keyin.entity.Aircraft;
import com.keyin.entity.Flight;
import com.keyin.repository.AircraftRepository;
import com.keyin.repository.FlightRepository;
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

    public void deleteAircraft(Long id) {
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aircraft not found with ID: " + id));
        aircraftRepository.delete(aircraft);
    }

    public List<Flight> getFlightsByAircraftId(Long aircraftId) {
        return flightRepository.findByAircraft_Id(aircraftId);
    }

}
