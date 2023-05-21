package mk.ukim.finki.emt.paymentcatalog.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.paymentcatalog.domain.exceptions.PaymentAlreadyProcessedException;
import mk.ukim.finki.emt.paymentcatalog.domain.exceptions.PaymentNotFoundException;
import mk.ukim.finki.emt.paymentcatalog.domain.models.Payment;
import mk.ukim.finki.emt.paymentcatalog.domain.models.PaymentId;
import mk.ukim.finki.emt.paymentcatalog.service.PaymentService;
import mk.ukim.finki.emt.paymentcatalog.service.forms.PaymentForm;
import mk.ukim.finki.emt.sharedkernel.domain.PaymentStatus;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor
public class PaymentResource {
    private final PaymentService paymentService;

    @GetMapping
    public List<Payment> getAll() {
        return paymentService.getAllPayments();
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Money amount,
                                                 @RequestBody PaymentStatus status,
                                                 @RequestParam String orderId,
                                                 @RequestParam String paymentMethod) {
        Payment payment = paymentService.createPayment(amount, status, orderId, paymentMethod);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable("paymentId") PaymentId paymentId) {
        Optional<Payment> payment = paymentService.getPaymentById(paymentId);
        return payment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{paymentId}/cancel")
    public ResponseEntity<String> cancelPayment(@PathVariable("paymentId") PaymentId paymentId) {
        try {
            paymentService.cancelPayment(paymentId);
            return ResponseEntity.ok("Payment canceled successfully.");
        } catch (PaymentNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (PaymentAlreadyProcessedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Payment has already been processed and cannot be canceled.");
        }
    }
}
