package org.example.paymentService.api;

import org.example.paymentService.api.resource.PaymentInstrumentCardRequest;
import org.example.paymentService.api.resource.PaymentInstrumentCardResponse;
import org.example.paymentService.service.PaymentInstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment-instrument")
public class PaymentInstrumentController {

    private PaymentInstrumentService paymentInstrumentService;

    @Autowired
    public PaymentInstrumentController(PaymentInstrumentService paymentInstrumentService) {
        this.paymentInstrumentService = paymentInstrumentService;
    }

    @PostMapping
    public PaymentInstrumentCardResponse save(@RequestBody PaymentInstrumentCardRequest paymentInstrumentCardRequest) {
        return (PaymentInstrumentCardResponse) paymentInstrumentService.save(paymentInstrumentCardRequest);
    }
}
