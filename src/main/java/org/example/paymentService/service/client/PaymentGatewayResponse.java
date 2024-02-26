package org.example.paymentService.service.client;

public record PaymentGatewayResponse(
    String transactionId,
    String status,
    String message
) {

    public Boolean isSuccess() {
        return "SUCCESS".equals(status);
    }
}
