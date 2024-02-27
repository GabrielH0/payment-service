package org.example.paymentService.mapper;

import org.example.paymentService.api.resource.PaymentInstrumentCardRequest;
import org.example.paymentService.api.resource.PaymentInstrumentPixRequest;
import org.example.paymentService.model.PaymentInstrumentCard;
import org.example.paymentService.model.PaymentInstrumentPix;
import org.example.paymentService.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class PaymentInstrumentMapperTest {

    private PaymentInstrumentMapper paymentInstrumentMapper;

    @BeforeEach
    public void init() {
        this.paymentInstrumentMapper = Mappers.getMapper(PaymentInstrumentMapper.class);
    }

    @Test
    public void mapPaymentInstrumentToPaymentShouldBeEqualsTest() {
        PaymentInstrumentCardRequest paymentInstrumentCardRequest = TestUtils.getPaymentInstrumentCardRequest();

        PaymentInstrumentCard paymentInstrumentCard = (PaymentInstrumentCard) paymentInstrumentMapper.map(paymentInstrumentCardRequest);

        Assertions.assertEquals(paymentInstrumentCardRequest.getPaymentInstrumentId(), paymentInstrumentCard.getPaymentInstrumentId());
        Assertions.assertEquals(paymentInstrumentCardRequest.getUserId(), paymentInstrumentCard.getUserId());
        Assertions.assertEquals(paymentInstrumentCardRequest.getPaymentInstrumentType(), paymentInstrumentCard.getPaymentInstrumentType());
        Assertions.assertEquals(paymentInstrumentCardRequest.getCardToken(), paymentInstrumentCard.getCardToken());
    }

    @Test
    public void mapPaymentInstrumentPixToPaymentShouldBeEqualsTest() {
        PaymentInstrumentPixRequest paymentInstrumentPix = TestUtils.getPaymentInstrumentPixRequest();

        PaymentInstrumentPix paymentInstrument = (PaymentInstrumentPix) paymentInstrumentMapper.map(paymentInstrumentPix);

        Assertions.assertEquals(paymentInstrumentPix.getPaymentInstrumentId(), paymentInstrument.getPaymentInstrumentId());
        Assertions.assertEquals(paymentInstrumentPix.getUserId(), paymentInstrument.getUserId());
    }
}
