package mk.ukim.finki.emt.sharedkernel.domain.address;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

@Embeddable
@Getter
public class Address implements ValueObject {
    private final String street;
    private final String city;
    private final String postalCode;

    protected Address() {
        this.street = "";
        this.city = "";
        this.postalCode = "";
    }

    public Address(String street, String city, String postalCode) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

}
