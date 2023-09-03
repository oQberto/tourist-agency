package by.travel.touristagency.repository;

import by.travel.touristagency.entity.Booking;
import by.travel.touristagency.entity.Voucher;
import by.travel.touristagency.util.HibernateTestUtil;
import by.travel.touristagency.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class BookingRepositoryTest {
    private static final SessionFactory SESSION_FACTORY = HibernateTestUtil.buildSessionFactory();
    private BookingRepository bookingRepository;
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
        bookingRepository = new BookingRepository(session);
    }

    @AfterEach
    public void closeSession() {
        session.close();
    }


    @Test
    void getVouchersByUserId() {
        session.beginTransaction();

        List<Voucher> actualResult = bookingRepository.getVouchersByUserId(1L);
        assertThat(actualResult).hasSize(5);

        List<String> voucherNames = actualResult.stream()
                .map(Voucher::getName)
                .toList();
        assertThat(voucherNames).contains("Voucher9", "Voucher", "Voucher7", "Voucher5", "Voucher6");

        session.getTransaction().commit();
    }

    @Test
    void getBookingByVoucherIdAndUserId() {
        session.beginTransaction();

        Optional<Booking> actualResult = bookingRepository.getBookingByVoucherIdAndUserId(1L, 1L);
        assertThat(actualResult).isPresent();

        session.getTransaction().commit();
    }
}