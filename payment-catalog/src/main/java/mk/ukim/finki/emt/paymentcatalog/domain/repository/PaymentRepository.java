package mk.ukim.finki.emt.paymentcatalog.domain.repository;

import mk.ukim.finki.emt.paymentcatalog.domain.models.Payment;
import mk.ukim.finki.emt.paymentcatalog.domain.models.PaymentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, PaymentId> {
}
