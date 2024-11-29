package com.keyin.repository;

import com.keyin.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    Optional<Aircraft> findByAircraftId(String aircraftId);
    List<Aircraft> findByModel(String model);
}
