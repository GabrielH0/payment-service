package org.example.paymentService.mapper;

import org.example.paymentService.api.resource.PaymentRequest;
import org.example.paymentService.api.resource.PaymentResponse;
import org.example.paymentService.model.Payment;
import org.example.paymentService.model.PaymentInstrument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentResponse map(Payment payment);

    @Mapping(target = "paymentInstrument", source = "paymentInstrument")
    Payment map(PaymentRequest paymentRequest, PaymentInstrument paymentInstrument);
}
