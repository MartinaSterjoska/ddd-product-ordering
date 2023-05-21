package mk.ukim.finki.emt.sharedkernel.domain.events.payments;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.PaymentStatus;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter
public class PaymentCreated extends DomainEvent {

    private String paymentId;
    private Money amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String orderId;
    private String paymentMethod;

    public PaymentCreated(String topic) {
        super(TopicHolder.TOPIC_PAYMENT_CREATED);
    }

    public PaymentCreated(String paymentId, Money amount, PaymentStatus status, String orderId, String paymentMethod) {
        super(TopicHolder.TOPIC_PAYMENT_CREATED);
        this.paymentId = paymentId;
        this.amount = amount;
        this.status = status;
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;

    }
}
