package org.example.paymentService.api.resource;

import lombok.Data;
import org.example.paymentService.enums.CardType;
import org.example.paymentService.enums.PaymentInstrumentType;

import javax.validation.constraints.NotNull;

@Data
public class PaymentInstrumentCardRequest extends PaymentInstrumentResource {

    @NotNull
    private String cardToken;
    private CardType cardType;

    public PaymentInstrumentCardRequest(String paymentInstrumentId, Long userId,
                                        PaymentInstrumentType paymentInstrumentType, String cardToken) {
        super(paymentInstrumentId, userId, paymentInstrumentType);
        this.cardToken = cardToken;
    }

    public PaymentInstrumentCardRequest() {
    }
}
