package com.bus.reservation.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bus.reservation.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
