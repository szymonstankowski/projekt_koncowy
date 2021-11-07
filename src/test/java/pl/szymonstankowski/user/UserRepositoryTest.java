package pl.szymonstankowski.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void itShouldFindUserByName() {
        //given
        String name = "szymon";
        User user = new User("szymon", "szymon@wp.pl", "haslo", "ADMIN");
        underTest.save(user);

        //when
        Optional<User> expected = underTest.findUserByName(name);
        assertThat(expected).isPresent();

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