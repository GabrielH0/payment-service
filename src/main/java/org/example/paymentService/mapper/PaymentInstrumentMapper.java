package org.example.paymentService.mapper;

import org.example.paymentService.api.resource.PaymentInstrumentCardResponse;
import org.example.paymentService.api.resource.PaymentInstrumentCardRequest;
import org.example.paymentService.api.resource.PaymentInstrumentPixRequest;
import org.example.paymentService.api.resource.PaymentInstrumentResource;
import org.example.paymentService.enums.PaymentInstrumentType;
import org.example.paymentService.model.PaymentInstrument;
import org.example.paymentService.model.PaymentInstrumentCard;
import org.example.paymentService.model.PaymentInstrumentPix;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentInstrumentMapper {

    default PaymentInstrument map(PaymentInstrumentResource paymentInstrumentResource) {
        if (paymentInstrumentResource.getPaymentInstrumentType() == PaymentInstrumentType.PIX) {
            return mapToPix((PaymentInstrumentPixRequest) paymentInstrumentResource);
        }
        return mapToCard((PaymentInstrumentCardRequest) paymentInstrumentResource);
    }

    PaymentInstrumentPix mapToPix(PaymentInstrumentPixRequest paymentInstrumentRequest);

    @Mapping(target = "cardToken", source = "cardToken")
    PaymentInstrumentCard mapToCard(PaymentInstrumentCardRequest paymentInstrumentCardRequest);

    PaymentInstrumentCardResponse mapToCard(PaymentInstrumentCard paymentInstrument);

    default PaymentInstrumentResource map(PaymentInstrument paymentInstrument) {
        if (paymentInstrument instanceof PaymentInstrumentPix) {
            return null;
        }
        return mapToCard((PaymentInstrumentCard) paymentInstrument);
    }
}
