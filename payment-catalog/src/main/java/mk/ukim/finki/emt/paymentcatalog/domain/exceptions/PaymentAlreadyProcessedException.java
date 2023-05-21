package mk.ukim.finki.emt.paymentcatalog.domain.exceptions;

public class PaymentAlreadyProcessedException extends RuntimeException{

    public PaymentAlreadyProcessedException(String message) {
        super(message);
    }
}
