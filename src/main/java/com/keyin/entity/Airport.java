package com.keyin.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String iataCode;
    private String location;

    @OneToMany(mappedBy = "departureAirport")
    @JsonManagedReference
    @JsonBackReference
    private List<Flight> departures;

    @OneToMany(mappedBy = "arrivalAirport")
    @JsonManagedReference
    @JsonBackReference
    private List<Flight> arrivals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Flight> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Flight> departures) {
        this.departures = departures;
    }

    public List<Flight> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<Flight> arrivals) {
        this.arrivals = arrivals;
    }
}
