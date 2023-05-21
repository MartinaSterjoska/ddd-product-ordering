package mk.ukim.finki.emt.sharedkernel.domain.events.users;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.address.Address;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class UserCreated extends DomainEvent {

    private String userId;
    private String name;
    private int embg;
    private String phoneNumber;
    private String email;
    private Address address;

    public UserCreated(String topic) {
        super(TopicHolder.TOPIC_USER_CREATED);
    }

    public UserCreated(String userId, String name, int embg, String phoneNumber, String email, Address address) {
        super(TopicHolder.TOPIC_USER_CREATED);
        this.userId = userId;
        this.name = name;
        this.embg = embg;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
}
