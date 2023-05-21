package mk.ukim.finki.emt.paymentcatalog.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.PaymentStatus;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Entity
@Table(name="payment")
@Getter
public class Payment extends AbstractEntity<PaymentId> {
    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private String orderId;
    private String paymentMethod;

    public Payment() {
    }
    public Payment(Money amount) {
        super(PaymentId.randomId(PaymentId.class));
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
    }
    public static Payment build(Money amount, PaymentStatus status, String orderId, String paymentMethod) {
        Payment payment = new Payment();
        payment.amount = amount;
        payment.status = status;
        payment.orderId = orderId;
        payment.paymentMethod = paymentMethod;

        return payment;
    }

    public Payment(Money amount, PaymentStatus status, String orderId, String paymentMethod) {
        this.amount = amount;
        this.status = status;
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
    }

    public void process() {
        this.status = PaymentStatus.SUCCESS;
    }

    public void cancel() {
        this.status = PaymentStatus.CANCELED;
    }

    public void refund() {
        this.status = PaymentStatus.FAILED;

    }
}

