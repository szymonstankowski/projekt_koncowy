package pl.szymonstankowski.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String name);
    Optional<User> findUserByEmail(String email);
    User findUserById(Long id);
    void deleteById(Long id);

    //TODO MM: Tutaj ten @Transactional jest zbędny bo każda metoda w tym pliku będzie miała @Transactional.
    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);

}
