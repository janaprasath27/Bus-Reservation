package com.bus.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bus.reservation.model.BusDetails;
import com.bus.reservation.repository.BusDetailsRepository;
import com.bus.reservation.service.BusDetailsService;

@RestController
@RequestMapping("/api/bus-details")
public class BusDetailsController {

    @Autowired
    private BusDetailsService service;
    @Autowired
    private BusDetailsRepository busDetailsRepository;
    @GetMapping
    public List<BusDetails> getAllBusDetails() {
        return service.getAllBusDetails();
    }

    @PostMapping
    public void addBusDetails(@RequestBody BusDetails busDetails) {
        service.saveBusDetails(busDetails);
    }
       @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        if (busDetailsRepository.existsById(id)) {
            busDetailsRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/find")
    public List<BusDetails> getBusDetails(
            @RequestParam(value = "fromLocation", required = false) String fromLocation,
            @RequestParam(value = "toLocation", required = false) String toLocation) {

        if (fromLocation != null && toLocation != null && !fromLocation.isEmpty() && !toLocation.isEmpty()) {
            // Query database for matching bus details
            List<BusDetails> buses = busDetailsRepository.findByFromLocationAndToLocation(fromLocation, toLocation);
            if (buses.isEmpty()) {
                return List.of(); // Return an empty list if no results are found
            }
            return buses;
        } else {
            // Return all buses if no filters are applied
            return busDetailsRepository.findAll();
        }
    }
}
