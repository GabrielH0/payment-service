package org.example.paymentService.exceptions;

public class PaymentInstrumentNotFoundException extends RuntimeException {

    public PaymentInstrumentNotFoundException(String message) {
        super(message);
    }
}
