package org.example.paymentService.messaging;

import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.model.Topic;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.paymentService.event.PaymentEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentMessageProducerImpl implements PaymentEventProducer {

    private final AmazonSNSAsync amazonSNSAsync;
    private final Topic snsPaymentsTopic;

    @Async
    @Override
    @EventListener(PaymentEvent.class)
    public void sendPaymentEvent(PaymentEvent paymentEvent) {
        try {
            log.info("Sending message: {}", paymentEvent.getPayment());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            amazonSNSAsync.publishAsync(snsPaymentsTopic.getTopicArn(), objectMapper.writeValueAsString(paymentEvent.getPayment()));
            log.info("Message sent successfully");
        } catch (Exception e) {
           log.error("Error sending message: {}", e.getMessage(), e);
        }
    }
}
