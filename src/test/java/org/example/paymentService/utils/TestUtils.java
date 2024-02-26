package org.example.paymentService.utils;

import org.example.paymentService.api.resource.PaymentInstrumentResponse;
import org.example.paymentService.api.resource.PaymentRequest;
import org.example.paymentService.api.resource.PaymentResponse;
import org.example.paymentService.enums.CardType;
import org.example.paymentService.enums.PaymentInstrumentType;
import org.example.paymentService.enums.PaymentStatus;
import org.example.paymentService.model.Payment;
import org.example.paymentService.model.PaymentInstrument;
import org.example.paymentService.model.PaymentInstrumentCard;
import org.example.paymentService.service.client.PaymentGatewayResponse;

import java.math.BigInteger;
import java.time.Instant;

public class TestUtils {

    public static PaymentInstrumentCard getPaymentInstrumentCard(String paymentInstrumentId) {
        return new PaymentInstrumentCard(paymentInstrumentId, CardType.CREDIT);
    }

    public static PaymentRequest getPaymentRequest(String paymentInstrumentId) {
        return new PaymentRequest(paymentInstrumentId, "orderId", BigInteger.valueOf(100));
    }

    public static PaymentResponse getPaymentResponse(PaymentStatus paymentStatus) {
        return new PaymentResponse("paymentId",paymentStatus,
                new PaymentInstrumentResponse("paymentInstrumentId", PaymentInstrumentType.CARD),
                Instant.now(), "orderId", 1L, BigInteger.valueOf(100), null);
    }

    public static PaymentGatewayResponse getSuccessPaymentGatewayResponse() {
        return new PaymentGatewayResponse("transactionId",
                "SUCCESS", "Payment successful");
    }

    public static PaymentGatewayResponse getFailedPaymentGatewayResponse() {
        return new PaymentGatewayResponse("transactionId",
                "FAILED", "Payment failed");
    }

    public static Payment getPayment(String orderId, PaymentInstrument paymentInstrument) {
        return new Payment("paymentId", PaymentStatus.PAID, paymentInstrument, Instant.now(),
                orderId, "transactionId", 1L, BigInteger.valueOf(100), null);
    }
}
