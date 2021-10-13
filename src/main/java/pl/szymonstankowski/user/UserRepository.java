package pl.szymonstankowski.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String name);
    User findUserById(Long id);
    User findUserByEmail(String email);

    void deleteById(Long id);

}
