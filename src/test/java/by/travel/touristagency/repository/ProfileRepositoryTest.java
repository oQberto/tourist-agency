package by.travel.touristagency.repository;

import by.travel.touristagency.entity.Profile;
import by.travel.touristagency.util.HibernateTestUtil;
import by.travel.touristagency.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ProfileRepositoryTest {
    private static final SessionFactory SESSION_FACTORY = HibernateTestUtil.buildSessionFactory();
    private ProfileRepository profileRepository;
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
        profileRepository = ProfileRepository.getInstance(session);
    }

    @AfterEach
    public void closeSession() {
        session.close();
    }

    @Test
    void getProfileByUserId() {
        session.beginTransaction();

        Optional<Profile> profile = profileRepository.findById(1L);
        assertThat(profile).isPresent();

        Optional<Profile> actualResult = profileRepository.getProfileByUserId(1L);
        assertThat(actualResult).isPresent();
        assertThat(actualResult).isEqualTo(profile);

        session.getTransaction().commit();
    }
}