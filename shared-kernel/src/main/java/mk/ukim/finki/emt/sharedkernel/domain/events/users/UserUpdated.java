package mk.ukim.finki.emt.sharedkernel.domain.events.users;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.address.Address;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class UserUpdated extends DomainEvent {
    private String userId;
    private String updatedName;

    private int embg;
    private String phoneNumber;
    private String email;
    private Address address;

    public UserUpdated(String topic) {
        super(TopicHolder.TOPIC_USER_UPDATED);
    }

    public UserUpdated(String userId, String updatedName, int embg, String phoneNumber, String email, Address address) {
        super(TopicHolder.TOPIC_USER_UPDATED);
        this.userId = userId;
        this.updatedName = updatedName;
        this.embg = embg;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
}
