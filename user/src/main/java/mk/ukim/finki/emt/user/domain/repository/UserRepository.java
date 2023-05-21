package mk.ukim.finki.emt.user.domain.repository;

import mk.ukim.finki.emt.user.domain.models.User;
import mk.ukim.finki.emt.user.domain.models.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UserId> {
}
