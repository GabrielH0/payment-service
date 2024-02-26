package org.example.paymentService.api.resource;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public record PaymentRequest (
        @NotNull
        String paymentInstrumentId,
        @NotNull
        String orderId,
        @NotNull
        BigInteger amount
) {
}
