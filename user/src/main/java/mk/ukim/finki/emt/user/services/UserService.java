package mk.ukim.finki.emt.user.services;

import mk.ukim.finki.emt.sharedkernel.domain.address.Address;
import mk.ukim.finki.emt.user.domain.models.User;
import mk.ukim.finki.emt.user.domain.models.UserId;


import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(UserId userId);

    List<User> getAllUsers();

    User createUser(String name, int embg, String phoneNumber, String email, Address address);

    void updateUser(UserId userId);

    void deleteUser(UserId userId);
}
