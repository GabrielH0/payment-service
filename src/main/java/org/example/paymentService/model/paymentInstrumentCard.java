package org.example.paymentService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.paymentService.enums.CardType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class paymentInstrumentCard extends PaymentInstrument {
    private String cardToken;
    private CardType cardType;
}
