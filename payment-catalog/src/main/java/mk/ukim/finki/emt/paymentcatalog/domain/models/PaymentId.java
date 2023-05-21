package mk.ukim.finki.emt.paymentcatalog.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class PaymentId extends DomainObjectId {
    private PaymentId() {
        super(PaymentId.randomId(PaymentId.class).getId());
    }

    public PaymentId(String paymentId) {
        super(paymentId);
    }

    public static PaymentId of(String paymentId) {
        return new PaymentId(paymentId);
    }
}
