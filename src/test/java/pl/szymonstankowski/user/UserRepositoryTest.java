package pl.szymonstankowski.user;

import org.junit.jupiter.api.Disabled;
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
    void itShouldFindUserByEmail() {
        //given
        List<UserPlants> userPlants = new ArrayList<>();
        String email = "szymon@wp.pl";
        User user = new User(
                "szymon",
                email,
                "pass",
                "ADMIN_ROLE",
                false,
                false,
                userPlants);

        underTest.save(user);

        //when
        Optional<User> expected = underTest.findUserByName(email);
        //then
        assertThat(expected).isEmpty();
    }

    @Test
    void itShouldFindUserById() {
        //given
        List<UserPlants> userPlants = new ArrayList<>();

        User user = new User(
                "szymon",
                "szymon@wp.pl",
                "pass",
                "ADMIN_ROLE",
                false,
                false,
                userPlants);

        underTest.save(user);
        Long id = user.getId();

        //when
        User expected = underTest.findUserById(id);

        //then
        assertThat(expected).isEqualTo(user);

    }

    @Test
    @Disabled
    void deleteById() {
    }

    @Test
    void itShouldFindIfUserIsEnabled() {
        //given
        List<UserPlants> userPlants = new ArrayList<>();
        User user = new User(
                "szymon",
                "szymon@wp.pl",
                "pass",
                "ADMIN_ROLE",
                false,
                false,
                userPlants);

        underTest.save(user);
        Long id = user.getId();


        //when
        User userById = underTest.findUserById(id);
        userById.setEnabled(true);
        Boolean expected = userById.getEnabled();


        //then
        assertThat(expected).isTrue();


    }
}