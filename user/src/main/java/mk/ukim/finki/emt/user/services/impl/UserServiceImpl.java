package mk.ukim.finki.emt.user.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.address.Address;
import mk.ukim.finki.emt.user.domain.exceptions.UserNotFoundException;
import mk.ukim.finki.emt.user.domain.models.User;
import mk.ukim.finki.emt.user.domain.models.UserId;
import mk.ukim.finki.emt.user.domain.repository.UserRepository;

import mk.ukim.finki.emt.user.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> getUserById(UserId userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(String name, int embg, String phoneNumber, String email, Address address) {
        User user = User.build(name, embg, phoneNumber, email, address);
        return userRepository.save(user);
    }

    @Override
    public void updateUser(UserId userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(UserId userId) {
        userRepository.deleteById(userId);
    }
}
