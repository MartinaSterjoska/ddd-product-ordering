package mk.ukim.finki.emt.paymentcatalog.domain.exceptions;

public class PaymentIdNotExistException extends RuntimeException{

    public PaymentIdNotExistException(String message) {
        super(message);
    }
}
