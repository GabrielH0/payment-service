package org.example.paymentService.model;

import lombok.*;
import org.example.paymentService.enums.CardType;
import org.example.paymentService.enums.PaymentInstrumentType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Table
@Entity
@NoArgsConstructor
public class PaymentInstrumentCard extends PaymentInstrument {

    @Column(name = "card_token")
    private String cardToken;
    @Enumerated(value = javax.persistence.EnumType.STRING)
    @Column(name = "card_type")
    private CardType cardType;

    public PaymentInstrumentCard(String paymentInstrumentId, Long userId, String cardToken, CardType cardType) {
        super(paymentInstrumentId, userId);
        this.cardToken = cardToken;
        this.cardType = cardType;
    }

    @Override
    public PaymentInstrumentType getPaymentInstrumentType() {
        return PaymentInstrumentType.CARD;
    }
}
