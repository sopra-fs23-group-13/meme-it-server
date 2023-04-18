package ch.uzh.ifi.hase.soprafs23.service;

import ch.uzh.ifi.hase.soprafs23.entity.User;
import ch.uzh.ifi.hase.soprafs23.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the UserResource REST resource.
 *
 * @see UserService
 */
@WebAppConfiguration
@SpringBootTest
public class UserServiceIntegrationTest {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
    }

    @Test
    public void createUser_validInputs_success() {
        // given
        assertNull(userRepository.findBytoken("1"));

        User testUser = new User();
        testUser.setName("testName");
        testUser.setToken("1");

        // when
        User createdUser = userService.createUser(testUser);

        // then
        assertEquals(testUser.getName(), createdUser.getName());
        assertEquals(testUser.getToken(), createdUser.getToken());
        assertNotNull(createdUser.getToken());
    }

    @Test
    public void createUser_duplicateUsername_success() {
        assertNull(userRepository.findBytoken("1"));

        User testUser = new User();
        testUser.setName("testName");
        testUser.setToken("1");
        User createdUser = userService.createUser(testUser);

        // create second user with same username
        User testUser2 = new User();
        testUser2.setName("testName2");
        testUser2.setToken("2");
        User createdUser2 = userService.createUser(testUser2);

        // check that an error is thrown
        assertEquals(testUser.getName(), createdUser.getName());
        assertEquals(testUser.getToken(), createdUser.getToken());
        assertNotNull(createdUser.getToken());

        assertEquals(testUser2.getName(), createdUser2.getName());
        assertEquals(testUser2.getToken(), createdUser2.getToken());
        assertNotNull(createdUser2.getToken());
    }
}
