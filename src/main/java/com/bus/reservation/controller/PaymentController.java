package com.bus.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.reservation.model.Payment;
import com.bus.reservation.repository.PaymentRepository;



@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @PostMapping("/confirm-payment")
    public ResponseEntity<String> confirmPayment(@RequestBody Payment payment) {
        try {
            paymentRepository.save(payment);
            return ResponseEntity.ok("Payment data saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving payment data: " + e.getMessage());
        }
    }
}

