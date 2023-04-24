// package ch.uzh.ifi.hase.soprafs23.repository;

// import ch.uzh.ifi.hase.soprafs23.entity.User;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// @DataJpaTest
// public class UserRepositoryIntegrationTest {

//     @Autowired
//     private TestEntityManager entityManager;

//     @Autowired
//     private UserRepository userRepository;

//     @Test
//     public void findByName_success() {
//         // given
//         User user = new User();
//         user.setId("1");
//         user.setName("Firstname Lastname");

//         entityManager.persist(user);
//         entityManager.flush();

//         // when
//         User found = userRepository.findById(user.getId());

//         // then
//         assertNotNull(found.getId());
//         assertEquals(found.getName(), user.getName());
//     }
// }
