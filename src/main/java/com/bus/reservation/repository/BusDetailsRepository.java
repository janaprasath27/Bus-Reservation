package com.bus.reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bus.reservation.model.BusDetails;

public interface BusDetailsRepository extends JpaRepository<BusDetails, Long> {
    List<BusDetails> findByFromLocationAndToLocation(String fromLocation, String toLocation);
}
