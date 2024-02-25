package org.example.paymentService.api.resource;

import org.example.paymentService.enums.PaymentStatus;

import java.time.Instant;

public record PaymentResponse(
    String paymentId,
    PaymentStatus paymentStatus,
    PaymentInstrumentResponse paymentInstrument,
    Instant paymentDate,
    String orderId,
    Long userId) {
}
