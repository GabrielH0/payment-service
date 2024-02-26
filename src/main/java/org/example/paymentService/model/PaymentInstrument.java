package org.example.paymentService.model;

import lombok.*;
import org.example.paymentService.enums.PaymentInstrumentType;

import javax.persistence.*;

@Getter
@Setter
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public abstract class PaymentInstrument {

    @Id
    @GeneratedValue(generator = "uuid", strategy = GenerationType.AUTO)
    @Column(name = "payment_instrument_id")
    private String paymentInstrumentId;
    @Column(name = "user_id")
    private Long UserId;

    public abstract PaymentInstrumentType getPaymentInstrumentType();
}
