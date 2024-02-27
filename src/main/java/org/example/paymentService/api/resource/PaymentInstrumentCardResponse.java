package org.example.paymentService.api.resource;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.paymentService.enums.CardType;
import org.example.paymentService.enums.PaymentInstrumentType;

@Data
@NoArgsConstructor
public class PaymentInstrumentCardResponse extends PaymentInstrumentResource {
    private CardType cardType;

    public PaymentInstrumentCardResponse(String paymentInstrumentId, Long userId, PaymentInstrumentType paymentInstrumentType, CardType cardType) {
        super(paymentInstrumentId, userId, paymentInstrumentType);
        this.cardType = cardType;
    }
}
