package org.example.paymentService.mapper;

import org.example.paymentService.api.resource.PaymentRequest;
import org.example.paymentService.api.resource.PaymentResponse;
import org.example.paymentService.model.Payment;
import org.example.paymentService.model.PaymentInstrumentCard;
import org.example.paymentService.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class PaymentMapperTest {

    private PaymentMapper paymentMapper;

    @BeforeEach
    public void init() {
        this.paymentMapper = Mappers.getMapper(PaymentMapper.class);
    }

    @Test
    public void mapPaymentToPaymentResponseShouldBeEqualsTest() {
        Payment payment = TestUtils.getPayment("orderId",
                TestUtils.getPaymentInstrumentCard("paymentInstrumentId"));

        PaymentResponse paymentResponse = paymentMapper.map(payment);

        Assertions.assertEquals(paymentResponse.paymentId(), payment.getPaymentId());
        Assertions.assertEquals(paymentResponse.paymentStatus(), payment.getPaymentStatus());
        Assertions.assertEquals(paymentResponse.paymentInstrument().paymentInstrumentId(),
                payment.getPaymentInstrument().getPaymentInstrumentId());
        Assertions.assertEquals(paymentResponse.paymentDate(), payment.getPaymentDate());
        Assertions.assertEquals(paymentResponse.orderId(), payment.getOrderId());
        Assertions.assertEquals(paymentResponse.userId(), payment.getUserId());
        Assertions.assertEquals(paymentResponse.amount(), payment.getAmount());
        Assertions.assertEquals(paymentResponse.message(), payment.getMessage());
    }

    @Test
    public void mapPaymentRequestToPaymentShouldBeEqualsTest() {
        PaymentRequest paymentRequest = TestUtils.getPaymentRequest("paymentInstrumentId");
        PaymentInstrumentCard paymentInstrument = TestUtils.getPaymentInstrumentCard("paymentInstrumentId");

        Payment payment = paymentMapper.map(paymentRequest, paymentInstrument);

        Assertions.assertEquals(paymentInstrument.getPaymentInstrumentId(),
                payment.getPaymentInstrument().getPaymentInstrumentId());
        Assertions.assertEquals(paymentRequest.orderId(), payment.getOrderId());
        Assertions.assertEquals(paymentRequest.amount(), payment.getAmount());
    }
}
