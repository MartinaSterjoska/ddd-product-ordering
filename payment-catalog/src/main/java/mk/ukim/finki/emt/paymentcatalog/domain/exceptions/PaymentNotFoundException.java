package mk.ukim.finki.emt.paymentcatalog.domain.exceptions;

public class PaymentNotFoundException extends RuntimeException{

    public PaymentNotFoundException(String message){
        super(message);
    }
}
