package mk.ukim.finki.emt.sharedkernel.domain.events.users;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class UserDeleted extends DomainEvent {
    private String userId;
    private String name;

    public UserDeleted(String topic) {
        super(TopicHolder.TOPIC_USER_DELETED);
    }
    public UserDeleted(String userId, String name) {
        super(TopicHolder.TOPIC_USER_DELETED);
        this.userId = userId;
        this.name = name;
    }
}
