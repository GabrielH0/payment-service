package org.example.paymentService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.paymentService.enums.PaymentStatus;

import java.math.BigInteger;
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
    private BigInteger amount;
    private String message;

    public void pay(String transactionId) {
        this.paymentStatus = PaymentStatus.PAID;
        this.paymentDate = Instant.now();
        this.transactionId = transactionId;
    }

    public void reject(String message) {
        this.paymentStatus = PaymentStatus.REJECTED;
        this.paymentDate = Instant.now();
        this.message = message;
    }
}
