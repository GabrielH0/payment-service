package org.example.paymentService.api.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.paymentService.enums.PaymentInstrumentType;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PaymentInstrumentResource {
    private String paymentInstrumentId;
    @NotNull
    private Long userId;
    private PaymentInstrumentType paymentInstrumentType;
}
