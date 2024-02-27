package org.example.paymentService.repository;

import org.example.paymentService.model.PaymentInstrument;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PaymentInstrumentRepository extends CrudRepository<PaymentInstrument, String> {
    Optional<PaymentInstrument> findByPaymentInstrumentIdAndUserId(String paymentInstrumentId, Long userId);
}
