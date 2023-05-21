package mk.ukim.finki.emt.sharedkernel.domain.events.payments;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class PaymentDeleted extends DomainEvent {

    private String paymentId;

    public PaymentDeleted() {
        super(TopicHolder.TOPIC_PAYMENT_DELETED);
    }

    public PaymentDeleted(String paymentId) {
        super(TopicHolder.TOPIC_PAYMENT_DELETED);
        this.paymentId = paymentId;
    }
}
