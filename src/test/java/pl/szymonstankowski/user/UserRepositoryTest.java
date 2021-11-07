package pl.szymonstankowski.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.szymonstankowski.userPlants.UserPlants;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void itShouldFindUserByName() {
        //giver
        List<UserPlants> userPlants = new ArrayList<>();
        String name = "szymon";
        User user = new User(
                name,
                "szymon@wp.pl",
                "pass",
                "ADMIN_ROLE",
                false,
                false,
                userPlants);

        underTest.save(user);

        //when
        Optional<User> expected = underTest.findUserByName(name);
        //then
        assertThat(expected).isEmpty();


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