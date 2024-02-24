package org.example.paymentService.model;

import org.example.paymentService.enums.PaymentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentTest {

    @Test
    public void payShouldSetPaymentInformationTest() {
        Payment payment = new Payment();
        String transactionId = "transactionId";

        payment.pay(transactionId);

        Assertions.assertNotNull(payment.getPaymentStatus());
        Assertions.assertEquals(PaymentStatus.PAID, payment.getPaymentStatus());
        Assertions.assertNotNull(payment.getPaymentDate());
        Assertions.assertNotNull(payment.getTransactionId());
    }
}
