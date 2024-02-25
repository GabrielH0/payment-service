package org.example.paymentService.api.resource;

import org.example.paymentService.enums.PaymentInstrumentType;

public record PaymentInstrumentResponse(
    String paymentInstrumentId,
    PaymentInstrumentType paymentInstrumentType) {
}
