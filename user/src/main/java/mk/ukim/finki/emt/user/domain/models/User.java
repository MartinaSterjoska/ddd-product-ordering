package mk.ukim.finki.emt.user.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.address.Address;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;


@Entity
@Table(name = "user")
@Getter
public class User extends AbstractEntity {
    private String name;
    private int embg;
    private String phoneNumber;
    private String email;
    private Address address;

    public User() {
        super(UserId.randomId(UserId.class));
    }

    public static User build(String name, int embg, String phoneNumber, String email, Address address) {
        User user = new User();
        user.name = name;
        user.embg = embg;
        user.phoneNumber = phoneNumber;
        user.email = email;
        user.address = address;
        return user;
    }
}
