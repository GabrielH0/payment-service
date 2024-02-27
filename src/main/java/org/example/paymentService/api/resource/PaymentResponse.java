package org.example.paymentService.api.resource;

import org.example.paymentService.enums.PaymentStatus;

import java.math.BigInteger;
import java.time.Instant;

public record PaymentResponse(
    String paymentId,
    PaymentStatus paymentStatus,
    PaymentInstrumentResource paymentInstrument,
    Instant paymentDate,
    String orderId,
    Long userId,
    BigInteger amount,
    String message) {
}
