package org.example.paymentService.service;

import lombok.RequiredArgsConstructor;
import org.example.paymentService.api.resource.PaymentInstrumentResource;
import org.example.paymentService.exceptions.PaymentInstrumentNotFoundException;
import org.example.paymentService.mapper.PaymentInstrumentMapper;
import org.example.paymentService.model.PaymentInstrument;
import org.example.paymentService.repository.PaymentInstrumentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentInstrumentServiceImpl implements PaymentInstrumentService {

    private final PaymentInstrumentMapper paymentInstrumentMapper;
    private final PaymentInstrumentRepository paymentInstrumentRepository;

    @Override
    public PaymentInstrumentResource save(PaymentInstrumentResource paymentInstrumentResource) {
        PaymentInstrument map = paymentInstrumentMapper.map(paymentInstrumentResource);
        return paymentInstrumentMapper.map(paymentInstrumentRepository.save(map));
    }

    @Override
    public PaymentInstrument getPaymentInstrument(String paymentInstrumentId, Long userId) {
        return paymentInstrumentRepository.findByPaymentInstrumentIdAndUserId(paymentInstrumentId, userId)
                .orElseThrow(() -> new PaymentInstrumentNotFoundException("Payment instrument not found"));
    }
}
