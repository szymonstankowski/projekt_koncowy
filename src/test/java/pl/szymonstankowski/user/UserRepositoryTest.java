package pl.szymonstankowski.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void itShouldFindUserByName() {
    }

    @Test
    void findUserByEmail() {
    }

    @Test
    void findUserById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void enableUser() {
    }
}