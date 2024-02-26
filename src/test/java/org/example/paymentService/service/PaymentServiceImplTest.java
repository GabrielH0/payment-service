package org.example.paymentService.service;

import org.example.paymentService.api.resource.PaymentRequest;
import org.example.paymentService.api.resource.PaymentResponse;
import org.example.paymentService.enums.PaymentStatus;
import org.example.paymentService.mapper.PaymentMapper;
import org.example.paymentService.model.Payment;
import org.example.paymentService.model.PaymentInstrumentCard;
import org.example.paymentService.repository.PaymentRepository;
import org.example.paymentService.service.client.PaymentGatewayResponse;
import org.example.paymentService.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PaymentServiceImplTest {

    private PaymentRepository paymentRepository;
    private PaymentGatewayService paymentGatewayService;
    private PaymentMapper paymentMapper;
    private PaymentInstrumentService paymentInstrumentService;
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        paymentRepository = Mockito.mock(PaymentRepository.class);
        paymentGatewayService = Mockito.mock(PaymentGatewayService.class);
        paymentMapper = Mockito.mock(PaymentMapper.class);
        paymentInstrumentService = Mockito.mock(PaymentInstrumentService.class);
        paymentService = new PaymentServiceImpl(paymentGatewayService, paymentMapper, paymentRepository,
                paymentInstrumentService);
    }

    @Test
    public void payShouldReturnPaymentSuccessResponse() {
        String paymentInstrumentId = "paymentInstrumentId";
        String orderId = "orderId";
        PaymentInstrumentCard paymentInstrumentCard = TestUtils.getPaymentInstrumentCard(paymentInstrumentId);
        PaymentRequest paymentRequest = TestUtils.getPaymentRequest(paymentInstrumentId);
        PaymentResponse paymentResponse = TestUtils.getPaymentResponse(PaymentStatus.PAID);
        PaymentGatewayResponse paymentGatewayResponse = TestUtils.getSuccessPaymentGatewayResponse();
        Payment payment = TestUtils.getPayment(orderId, paymentInstrumentCard);
        Mockito.when(paymentInstrumentService.getPaymentInstrument(paymentRequest.paymentInstrumentId()))
                .thenReturn(paymentInstrumentCard);
        Mockito.when(paymentGatewayService.pay(Mockito.any())).thenReturn(paymentGatewayResponse);
        Mockito.when(paymentMapper.map(paymentRequest, paymentInstrumentCard)).thenReturn(payment);
        Mockito.when(paymentRepository.save(payment)).thenReturn(payment);
        Mockito.when(paymentMapper.map(payment)).thenReturn(paymentResponse);

        PaymentResponse response = paymentService.pay(paymentRequest);

        Mockito.verify(paymentGatewayService, Mockito.times(1)).pay(payment);
        Mockito.verify(paymentMapper, Mockito.times(1)).map(paymentRequest, paymentInstrumentCard);
        Mockito.verify(paymentRepository, Mockito.times(1)).save(payment);
        Mockito.verify(paymentMapper, Mockito.times(1)).map(payment);
        Assertions.assertEquals(paymentResponse.paymentId(), response.paymentId());
        Assertions.assertTrue(response.paymentStatus().equals(PaymentStatus.PAID));
    }

    @Test
    public void payShouldReturnNotPaidResponse() {
        String paymentInstrumentId = "paymentInstrumentId";
        String orderId = "orderId";
        PaymentInstrumentCard paymentInstrumentCard = TestUtils.getPaymentInstrumentCard(paymentInstrumentId);
        PaymentRequest paymentRequest = TestUtils.getPaymentRequest(paymentInstrumentId);
        PaymentResponse paymentResponse = TestUtils.getPaymentResponse(PaymentStatus.REJECTED);
        PaymentGatewayResponse paymentGatewayResponse = TestUtils.getFailedPaymentGatewayResponse();
        Payment payment = TestUtils.getPayment(orderId, paymentInstrumentCard);
        Mockito.when(paymentInstrumentService.getPaymentInstrument(paymentRequest.paymentInstrumentId()))
                .thenReturn(paymentInstrumentCard);
        Mockito.when(paymentGatewayService.pay(Mockito.any())).thenReturn(paymentGatewayResponse);
        Mockito.when(paymentMapper.map(paymentRequest, paymentInstrumentCard)).thenReturn(payment);
        Mockito.when(paymentRepository.save(payment)).thenReturn(payment);
        Mockito.when(paymentMapper.map(payment)).thenReturn(paymentResponse);

        PaymentResponse response = paymentService.pay(paymentRequest);

        Mockito.verify(paymentGatewayService, Mockito.times(1)).pay(payment);
        Mockito.verify(paymentMapper, Mockito.times(1)).map(paymentRequest, paymentInstrumentCard);
        Mockito.verify(paymentRepository, Mockito.times(1)).save(payment);
        Mockito.verify(paymentMapper, Mockito.times(1)).map(payment);
        Assertions.assertEquals(paymentResponse.paymentId(), response.paymentId());
        Assertions.assertTrue(response.paymentStatus().equals(PaymentStatus.REJECTED));
    }

    @Test
    public void payShouldSaveWhenErrorSendingToPaymentGateway() {
        String paymentInstrumentId = "paymentInstrumentId";
        String orderId = "orderId";
        PaymentInstrumentCard paymentInstrumentCard = TestUtils.getPaymentInstrumentCard(paymentInstrumentId);
        PaymentRequest paymentRequest = TestUtils.getPaymentRequest(paymentInstrumentId);
        Payment payment = TestUtils.getPayment(orderId, paymentInstrumentCard);
        Mockito.when(paymentInstrumentService.getPaymentInstrument(paymentRequest.paymentInstrumentId()))
                .thenReturn(paymentInstrumentCard);
        Mockito.when(paymentMapper.map(paymentRequest, paymentInstrumentCard)).thenReturn(payment);
        Mockito.when(paymentRepository.save(payment)).thenReturn(payment);
        Mockito.when(paymentGatewayService.pay(payment))
                .thenThrow(new RuntimeException("Error sending to payment gateway"));

        paymentService.pay(paymentRequest);

        Mockito.verify(paymentMapper, Mockito.times(1)).map(paymentRequest, paymentInstrumentCard);
        Mockito.verify(paymentRepository, Mockito.times(1)).save(payment);
    }
}
