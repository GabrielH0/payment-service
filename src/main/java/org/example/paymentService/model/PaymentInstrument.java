package org.example.paymentService.model;

import lombok.*;
import org.example.paymentService.enums.PaymentInstrumentType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class PaymentInstrument {
    private String paymentInstrumentId;
    private Long UserId;

    public abstract PaymentInstrumentType getPaymentInstrumentType();
}
