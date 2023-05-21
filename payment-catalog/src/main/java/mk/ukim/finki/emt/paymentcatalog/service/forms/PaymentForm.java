package mk.ukim.finki.emt.paymentcatalog.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.validation.constraints.NotNull;

@Data
public class PaymentForm {

    @NotNull
    private Money amount;
}
