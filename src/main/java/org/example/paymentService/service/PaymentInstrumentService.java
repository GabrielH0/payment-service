package org.example.paymentService.service;

import org.example.paymentService.model.PaymentInstrument;

public interface PaymentInstrumentService {

    PaymentInstrument getPaymentInstrument(String paymentInstrumentId, Long userId);
}
