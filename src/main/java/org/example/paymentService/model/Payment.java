package org.example.paymentService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.paymentService.enums.PaymentStatus;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.Instant;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(generator = "uuid", strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private String paymentId;
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;
    @ManyToOne
    @JoinColumn(name = "payment_instrument_id")
    private PaymentInstrument paymentInstrument;
    @Column(name = "payment_date")
    private Instant paymentDate;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "user_id")
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
