package org.example.paymentService.service;

import org.example.paymentService.api.resource.PaymentRequest;
import org.example.paymentService.api.resource.PaymentResponse;

public interface PaymentService {

    PaymentResponse pay(PaymentRequest paymentRequest, Long userId);
}
