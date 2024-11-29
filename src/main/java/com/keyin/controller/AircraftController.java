package com.keyin.controller;

import com.keyin.entity.Aircraft;
import com.keyin.entity.Flight;
import com.keyin.service.AircraftService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircrafts")
public class AircraftController {
    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping
    public List<Aircraft> getAllAircrafts() {
        return aircraftService.getAllAircrafts();
    }

    @GetMapping("/{aircraftId}")
    public Aircraft getAircraftByAircraftId(@PathVariable String aircraftId) {
        return aircraftService.getAircraftByAircraftId(aircraftId);
    }

    @GetMapping("/model/{model}")
    public List<Aircraft> getAircraftByModel(@PathVariable String model) {
        return aircraftService.getAircraftByModel(model);
    }

    @GetMapping("/{id}/flights")
    public List<Flight> getFlightsByAircraftId(@PathVariable Long id) {
        return aircraftService.getFlightsByAircraftId(id);
    }

    @PostMapping
    public Aircraft createAircraft(@RequestBody Aircraft aircraft) {
        return aircraftService.saveAircraft(aircraft);
    }

    @PutMapping("/{id}")
    public Aircraft updateAircraft(@PathVariable Long id, @RequestBody Aircraft updatedAircraft) {
        return aircraftService.updateAircraft(id, updatedAircraft);
    }

    @DeleteMapping("/{id}")
    public void deleteAircraft(@PathVariable Long id) {
        aircraftService.deleteAircraft(id);
    }
}

