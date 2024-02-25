package org.example.paymentService.api.resource;

import java.math.BigInteger;

public record PaymentRequest (
        String paymentInstrumentId,
        String orderId,
        BigInteger amount
) {
}
