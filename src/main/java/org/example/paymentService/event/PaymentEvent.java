package org.example.paymentService.event;

import lombok.Getter;
import org.example.paymentService.model.Payment;
import org.springframework.context.ApplicationEvent;

@Getter
public class PaymentEvent extends ApplicationEvent {

    private final Payment payment;

    public PaymentEvent(Payment payment, Object source) {
        super(source);
        this.payment = payment;
    }
}
