package org.example.paymentService.model;

import lombok.*;
import org.example.paymentService.enums.PaymentInstrumentType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public abstract class PaymentInstrument {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "payment_instrument_id")
    private String paymentInstrumentId;
    @Column(name = "user_id")
    private Long userId;

    public abstract PaymentInstrumentType getPaymentInstrumentType();
}
