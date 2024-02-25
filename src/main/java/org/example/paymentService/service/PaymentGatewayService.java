package org.example.paymentService.service;

import org.example.paymentService.model.Payment;
import org.example.paymentService.service.client.PaymentGatewayResponse;

public interface PaymentGatewayService {
    PaymentGatewayResponse pay(Payment Payment);
}
