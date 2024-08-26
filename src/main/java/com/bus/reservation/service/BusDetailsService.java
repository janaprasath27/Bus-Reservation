package com.bus.reservation.service;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.reservation.model.BusDetails;
import com.bus.reservation.repository.BusDetailsRepository;

@Service
public class BusDetailsService {

    @Autowired
    private BusDetailsRepository repository;

    public List<BusDetails> getAllBusDetails() {
        return repository.findAll();
    }

    public void saveBusDetails(BusDetails busDetails) {
        repository.save(busDetails);
    }
// Example of service method to fetch buses
public List<BusDetails> findBuses(String fromLocation, String toLocation) {
    // Example of a method to find buses based on location
    // This is pseudo-code; your actual implementation may vary
    if (fromLocation != null && toLocation != null) {
        return repository.findByFromLocationAndToLocation(fromLocation, toLocation);
    } else {
        return Collections.emptyList();
    }
}

}
