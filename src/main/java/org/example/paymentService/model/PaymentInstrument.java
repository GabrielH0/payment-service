package org.example.paymentService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class PaymentInstrument {
    private String paymentInstrumentId;
    private Long UserId;
}
