package org.example.paymentService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.paymentService.api.resource.PaymentRequest;
import org.example.paymentService.api.resource.PaymentResponse;
import org.example.paymentService.mapper.PaymentMapper;
import org.example.paymentService.model.Payment;
import org.example.paymentService.model.PaymentInstrument;
import org.example.paymentService.repository.PaymentRepository;
import org.example.paymentService.service.client.PaymentGatewayResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentGatewayService paymentGatewayService;
    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private final PaymentInstrumentService paymentInstrumentService;

    @Override
    public PaymentResponse pay(PaymentRequest paymentRequest) {
        PaymentInstrument paymentInstrument = paymentInstrumentService
                .getPaymentInstrument(paymentRequest.paymentInstrumentId());
        Payment payment = paymentMapper.map(paymentRequest, paymentInstrument);
        PaymentGatewayResponse pay = sendToPaymentGateway(payment);
        setPaymentResult(pay, payment);
        paymentRepository.save(payment);
        return paymentMapper.map(payment);
    }

    private PaymentGatewayResponse sendToPaymentGateway(Payment payment) {
        try {
            return paymentGatewayService.pay(payment);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private static void setPaymentResult(PaymentGatewayResponse pay, Payment payment) {
        if (pay == null) {
            return;
        }
        if (pay.isSuccess()) {
            payment.pay(pay.transactionId());
            return;
        }
        payment.reject(pay.message());
    }
}
