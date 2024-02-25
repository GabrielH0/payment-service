package org.example.paymentService.mapper;

import org.example.paymentService.api.resource.PaymentRequest;
import org.example.paymentService.api.resource.PaymentResponse;
import org.example.paymentService.model.Payment;

public interface PaymentMapper {

    PaymentResponse map(Payment payment);

    Payment map(PaymentRequest paymentRequest);
}