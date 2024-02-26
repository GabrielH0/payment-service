package org.example.paymentService.repository;

import org.example.paymentService.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, String> {
}
