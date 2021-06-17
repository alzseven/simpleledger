package kr.ac.jejunu.simpleledger.user.repository;

import kr.ac.jejunu.simpleledger.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);

    Optional<User> findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String userName);

    Optional<User> findByUsername(String username);
}
