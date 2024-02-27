package org.example.paymentService.service;

import org.example.paymentService.api.resource.PaymentInstrumentCardRequest;
import org.example.paymentService.api.resource.PaymentInstrumentResource;
import org.example.paymentService.mapper.PaymentInstrumentMapper;
import org.example.paymentService.model.PaymentInstrument;
import org.example.paymentService.model.PaymentInstrumentCard;
import org.example.paymentService.repository.PaymentInstrumentRepository;
import org.example.paymentService.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class PaymentInstrumentServiceImplTest {

    private PaymentInstrumentService paymentInstrumentService;
    private PaymentInstrumentMapper paymentInstrumentMapper;
    private PaymentInstrumentRepository paymentInstrumentRepository;

    @BeforeEach
    public void init() {
        this.paymentInstrumentMapper = Mockito.mock(PaymentInstrumentMapper.class);
        this.paymentInstrumentRepository = Mockito.mock(PaymentInstrumentRepository.class);
        this.paymentInstrumentService = new PaymentInstrumentServiceImpl(paymentInstrumentMapper, paymentInstrumentRepository);
    }

    @Test
    public void saveTest() {
        PaymentInstrumentCardRequest paymentInstrumentCardRequest = TestUtils.getPaymentInstrumentCardRequest();
        PaymentInstrumentCard paymentInstrumentCard = new PaymentInstrumentCard(paymentInstrumentCardRequest.getPaymentInstrumentId(),
                paymentInstrumentCardRequest.getUserId(), paymentInstrumentCardRequest.getCardToken(),
                paymentInstrumentCardRequest.getCardType());

        Mockito.when(paymentInstrumentMapper.map(paymentInstrumentCardRequest)).thenReturn(paymentInstrumentCard);
        Mockito.when(paymentInstrumentRepository.save(paymentInstrumentCard)).thenReturn(paymentInstrumentCard);
        Mockito.when(paymentInstrumentMapper.map(paymentInstrumentCard)).thenReturn(paymentInstrumentCardRequest);

        PaymentInstrumentResource paymentInstrumentResource = paymentInstrumentService.save(paymentInstrumentCardRequest);

        Mockito.verify(paymentInstrumentMapper, Mockito.times(1)).map(paymentInstrumentCardRequest);
        Mockito.verify(paymentInstrumentRepository, Mockito.times(1)).save(paymentInstrumentCard);
        Assertions.assertEquals(paymentInstrumentCardRequest.getPaymentInstrumentId(), paymentInstrumentResource.getPaymentInstrumentId());
        Assertions.assertEquals(paymentInstrumentCardRequest.getUserId(), paymentInstrumentResource.getUserId());
        Assertions.assertEquals(paymentInstrumentCardRequest.getPaymentInstrumentType(), paymentInstrumentResource.getPaymentInstrumentType());
    }

    @Test
    public void getPaymentInstrumentTest() {
        PaymentInstrumentCard paymentInstrument = TestUtils.getPaymentInstrumentCard("paymentInstrumentId");

        Mockito.when(paymentInstrumentRepository
                        .findByPaymentInstrumentIdAndUserId(paymentInstrument.getPaymentInstrumentId(), paymentInstrument.getUserId()))
                .thenReturn(Optional.of(paymentInstrument));

        PaymentInstrument paymentInstrumentResponse = paymentInstrumentService.getPaymentInstrument("paymentInstrumentId", paymentInstrument.getUserId());

        Mockito.verify(paymentInstrumentRepository, Mockito.times(1))
                .findByPaymentInstrumentIdAndUserId(paymentInstrument.getPaymentInstrumentId(), paymentInstrument.getUserId());
        Assertions.assertEquals(paymentInstrument.getPaymentInstrumentId(), paymentInstrumentResponse.getPaymentInstrumentId());
    }

    @Test
    public void getPaymentInstrumentNotFoundTest() {
        PaymentInstrumentCard paymentInstrument = TestUtils.getPaymentInstrumentCard("paymentInstrumentId");

        Mockito.when(paymentInstrumentRepository
                        .findByPaymentInstrumentIdAndUserId(paymentInstrument.getPaymentInstrumentId(),
                                paymentInstrument.getUserId()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class,
                () -> paymentInstrumentService
                        .getPaymentInstrument("paymentInstrumentId", paymentInstrument.getUserId()));

    }
}
