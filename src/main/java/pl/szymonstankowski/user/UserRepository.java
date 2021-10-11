package pl.szymonstankowski.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String name);
    User findUserById(Long id);
    User findUserByEmail(String email);

    User deleteUserByName(String name);
}
