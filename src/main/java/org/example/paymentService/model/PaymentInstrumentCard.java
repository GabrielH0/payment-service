package org.example.paymentService.model;

import lombok.*;
import org.example.paymentService.enums.CardType;
import org.example.paymentService.enums.PaymentInstrumentType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInstrumentCard extends PaymentInstrument {
    private String cardToken;
    private CardType cardType;

    @Override
    public PaymentInstrumentType getPaymentInstrumentType() {
        return PaymentInstrumentType.CARD;
    }
}
