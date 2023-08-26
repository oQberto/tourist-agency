package by.travel.touristagency.repository;

import by.travel.touristagency.entity.User;
import by.travel.touristagency.util.HibernateTestUtil;
import by.travel.touristagency.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest {
    private static final SessionFactory SESSION_FACTORY = HibernateTestUtil.buildSessionFactory();
    private UserRepository userRepository;
    private Session session;

    @BeforeAll
    public static void initDb() {
        TestDataImporter.importData(SESSION_FACTORY);
    }

    @AfterAll
    public static void closeDb() {
        SESSION_FACTORY.close();
    }

    @BeforeEach
    public void openSession() {
        session = SESSION_FACTORY.openSession();
        userRepository = new UserRepository(session);
    }

    @AfterEach
    public void closeSession() {
        session.close();
    }

    @Test
    void shouldFindUserById() {
        session.beginTransaction();

        Optional<User> actualResult = userRepository.findById(1L);

        assertThat(actualResult).isPresent();
        assertThat(actualResult.get().getEmail()).isEqualTo("uname@gmail.com");

        session.getTransaction().commit();
    }

    @Test
    void shouldNotFindUserIfDoesntExist() {
        session.beginTransaction();

        userRepository.delete(4L);
        Optional<User> actualResult = userRepository.findById(4L);

        assertThat(actualResult).isEmpty();

        session.getTransaction().rollback();
    }

    @Test
    void updateExistingUser() {
        session.beginTransaction();

        Optional<User> actualResult = userRepository.findById(5L);
        assertThat(actualResult).isPresent();

        User user = actualResult.get();
        user.setEmail("newmail@mail.com");
        userRepository.update(user);

        Optional<User> actualResultAfterUpdate = userRepository.findById(5L);
        assertThat(actualResultAfterUpdate).isPresent();
        assertThat(actualResultAfterUpdate.get()).isEqualTo(user);

        session.getTransaction().rollback();
    }

    @Test
    void shouldSaveIfUserDoesNotExist() {
        session.beginTransaction();

        User user = User.builder()
                .username("Name")
                .password("456")
                .email("name@mail.com")
                .company(userRepository.findById(1L).get().getCompany())
                .build();

        User savedUser = userRepository.save(user);

        Optional<User> actualResult = userRepository.findById(savedUser.getId());
        assertThat(actualResult).isPresent();
        assertThat(actualResult.get().getEmail()).isEqualTo("name@mail.com");

        session.getTransaction().commit();
    }

    @Test
    void shouldDeleteIfUserExists() {
        session.beginTransaction();

        Optional<User> actualResult = userRepository.findById(6L);
        assertThat(actualResult).isPresent();

        userRepository.delete(actualResult.get().getId());

        Optional<User> userAfterDelete = userRepository.findById(6L);
        assertThat(userAfterDelete).isEmpty();

        session.getTransaction().rollback();
    }

    @Test
    void shouldFindUserByEmailAndPassword() {
        session.beginTransaction();

        Optional<User> user = userRepository.findById(1L);
        assertThat(user).isPresent();
        String email = "uname@gmail.com";
        String password = "123";

        Optional<User> actualResult = userRepository.findByEmailAndPassword(email, password);
        assertThat(actualResult).isPresent();
        assertThat(actualResult).isEqualTo(user);

        session.getTransaction().commit();
    }
}