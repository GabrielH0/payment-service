package org.example.paymentService.service;

import org.example.paymentService.api.resource.PaymentInstrumentResource;
import org.example.paymentService.model.PaymentInstrument;

public interface PaymentInstrumentService {

    PaymentInstrumentResource save(PaymentInstrumentResource paymentInstrumentResource);

    PaymentInstrument getPaymentInstrument(String paymentInstrumentId, Long userId);
}
