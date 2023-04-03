package ch.uzh.ifi.hase.soprafs23.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ch.uzh.ifi.hase.soprafs23.entity.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
}