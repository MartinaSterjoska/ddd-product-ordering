package mk.ukim.finki.emt.paymentcatalog.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.paymentcatalog.domain.exceptions.PaymentAlreadyProcessedException;
import mk.ukim.finki.emt.paymentcatalog.domain.exceptions.PaymentIdNotExistException;
import mk.ukim.finki.emt.paymentcatalog.domain.models.Payment;
import mk.ukim.finki.emt.paymentcatalog.domain.models.PaymentId;
import mk.ukim.finki.emt.paymentcatalog.domain.repository.PaymentRepository;
import mk.ukim.finki.emt.paymentcatalog.service.PaymentService;
import mk.ukim.finki.emt.sharedkernel.domain.PaymentStatus;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Money amount, PaymentStatus status, String orderId, String paymentMethod) {
        Payment payment = Payment.build(amount, status, orderId, paymentMethod);

        paymentRepository.save(payment);

        return payment;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> getPaymentById(PaymentId paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public void processPayment(PaymentId paymentId) throws PaymentIdNotExistException, PaymentAlreadyProcessedException {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            if (payment.getStatus() == PaymentStatus.PENDING) {
                payment.process();
                paymentRepository.save(payment);
            } else {
                throw new PaymentAlreadyProcessedException("Payment has already been processed.");
            }
        } else {
            throw new PaymentIdNotExistException("Payment ID does not exist.");
        }
    }

    @Override
    public void cancelPayment(PaymentId paymentId) throws PaymentIdNotExistException, PaymentAlreadyProcessedException {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            if (payment.getStatus() == PaymentStatus.PENDING) {
                payment.cancel();
                paymentRepository.save(payment);
            } else {
                throw new PaymentAlreadyProcessedException("Payment has already been processed.");
            }
        } else {
            throw new PaymentIdNotExistException("Payment ID does not exist.");
        }
    }

    @Override
    public void refundPayment(PaymentId paymentId) throws PaymentIdNotExistException, PaymentAlreadyProcessedException {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            if (payment.getStatus() == PaymentStatus.SUCCESS) {
                payment.refund();
                paymentRepository.save(payment);
            } else {
                throw new PaymentAlreadyProcessedException("Payment has already been processed.");
            }
        } else {
            throw new PaymentIdNotExistException("Payment ID does not exist.");
        }
    }
}
