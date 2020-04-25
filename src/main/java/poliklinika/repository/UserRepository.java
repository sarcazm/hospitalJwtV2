package poliklinika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poliklinika.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}