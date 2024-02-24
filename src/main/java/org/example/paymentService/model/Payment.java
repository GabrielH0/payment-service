package org.example.paymentService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.paymentService.enums.PaymentStatus;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private String paymentId;
    private PaymentStatus paymentStatus;
    private PaymentInstrument paymentInstrument;
    private Instant paymentDate;
    private String orderId;
    private String transactionId;
    private Long userId;

    public void pay(String transactionId) {
        this.paymentStatus = PaymentStatus.PAID;
        this.paymentDate = Instant.now();
        this.transactionId = transactionId;
    }
}
