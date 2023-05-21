package mk.ukim.finki.emt.user.xport.events;


import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.events.users.UserCreated;
import mk.ukim.finki.emt.sharedkernel.domain.events.users.UserDeleted;
import mk.ukim.finki.emt.sharedkernel.domain.events.users.UserUpdated;
import mk.ukim.finki.emt.user.domain.models.User;
import mk.ukim.finki.emt.user.domain.models.UserId;
import mk.ukim.finki.emt.user.services.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserEventListener {

    private final UserService userService;

    @KafkaListener(topics= TopicHolder.TOPIC_USER_CREATED, groupId = "user")
    public void consumeUserCreatedEvent(String jsonMessage) {
        try {
            UserCreated event = DomainEvent.fromJson(jsonMessage,UserCreated.class);
            userService.createUser(event.getName(), event.getEmbg(), event.getPhoneNumber(), event.getEmail(), event.getAddress());
        } catch (Exception e){

        }

    }

    @KafkaListener(topics= TopicHolder.TOPIC_USER_UPDATED, groupId = "user")
    public void consumeUserUpdatedEvent(String jsonMessage) {
        try {
            UserUpdated event = DomainEvent.fromJson(jsonMessage,UserUpdated.class);
            userService.updateUser(UserId.of(event.getUserId()));
        } catch (Exception e){
        }

    }

    @KafkaListener(topics= TopicHolder.TOPIC_USER_DELETED, groupId = "user")
    public void consumeUserDeletedEvent(String jsonMessage) {
        try {
            UserDeleted event = DomainEvent.fromJson(jsonMessage,UserDeleted.class);
            userService.deleteUser(UserId.of(event.getUserId()));
        } catch (Exception e){

        }

    }
}
