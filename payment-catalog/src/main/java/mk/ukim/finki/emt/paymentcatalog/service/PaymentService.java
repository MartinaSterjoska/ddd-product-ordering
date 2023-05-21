package mk.ukim.finki.emt.paymentcatalog.service;

import mk.ukim.finki.emt.paymentcatalog.domain.exceptions.PaymentAlreadyProcessedException;
import mk.ukim.finki.emt.paymentcatalog.domain.exceptions.PaymentIdNotExistException;
import mk.ukim.finki.emt.paymentcatalog.domain.models.Payment;
import mk.ukim.finki.emt.paymentcatalog.domain.models.PaymentId;
import mk.ukim.finki.emt.sharedkernel.domain.PaymentStatus;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment createPayment(Money amount, PaymentStatus status, String orderId, String paymentMethod);

    List<Payment> getAllPayments();

    Optional<Payment> getPaymentById(PaymentId paymentId);

    void processPayment(PaymentId paymentId) throws PaymentIdNotExistException, PaymentAlreadyProcessedException;

    void cancelPayment(PaymentId paymentId) throws PaymentIdNotExistException, PaymentAlreadyProcessedException;

    void refundPayment(PaymentId paymentId) throws PaymentIdNotExistException, PaymentAlreadyProcessedException;
}
