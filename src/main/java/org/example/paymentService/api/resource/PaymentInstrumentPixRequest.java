package org.example.paymentService.api.resource;

import lombok.Data;
import org.example.paymentService.enums.PaymentInstrumentType;

@Data
public class PaymentInstrumentPixRequest extends PaymentInstrumentResource {


    public PaymentInstrumentPixRequest(String paymentInstrumentId, Long userId,
                                       PaymentInstrumentType paymentInstrumentType) {
        super(paymentInstrumentId, userId, paymentInstrumentType);
    }
}
