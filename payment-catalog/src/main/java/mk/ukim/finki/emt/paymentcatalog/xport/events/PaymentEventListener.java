package mk.ukim.finki.emt.paymentcatalog.xport.events;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.paymentcatalog.domain.models.PaymentId;
import mk.ukim.finki.emt.paymentcatalog.service.PaymentService;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.events.payments.PaymentCreated;
import mk.ukim.finki.emt.sharedkernel.domain.events.payments.PaymentDeleted;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentEventListener {

    private final PaymentService paymentService;

    @KafkaListener(topics = TopicHolder.TOPIC_PAYMENT_CREATED, groupId = "paymentCatalog")
    public void consumePaymentCreatedEvent(String jsonMessage) {
        try {
            PaymentCreated event = DomainEvent.fromJson(jsonMessage, PaymentCreated.class);
            paymentService.createPayment(event.getAmount(), event.getStatus(), event.getOrderId(),event.getPaymentMethod());
        } catch (Exception e) {
        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_PAYMENT_CREATED, groupId = "paymentCatalog")
    public void consumePaymentDeletedEvent(String jsonMessage) {
        try {
            PaymentDeleted event = DomainEvent.fromJson(jsonMessage, PaymentDeleted.class);
            paymentService.cancelPayment(PaymentId.of(event.getPaymentId()));
        } catch (Exception e) {
        }
    }
}
