package org.example.paymentService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.paymentService.enums.PaymentInstrumentType;

@Getter
@Setter
@AllArgsConstructor
public class PaymentInstrumentPix extends PaymentInstrument {

    @Override
    public PaymentInstrumentType getPaymentInstrumentType() {
        return PaymentInstrumentType.PIX;
    }
}
