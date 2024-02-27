package org.example.paymentService.messaging;

import org.example.paymentService.event.PaymentEvent;

public interface PaymentEventProducer {

    void sendPaymentEvent(PaymentEvent paymentEvent);
}
