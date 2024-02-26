package org.example.paymentService.service;

import org.example.paymentService.model.Payment;
import org.example.paymentService.service.client.PaymentGatewayResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayServiceImpl implements PaymentGatewayService {
    @Override
    public PaymentGatewayResponse pay(Payment Payment) {
        return new PaymentGatewayResponse("1", "SUCCESS", null);
    }
}
