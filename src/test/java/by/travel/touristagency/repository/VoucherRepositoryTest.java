package by.travel.touristagency.repository;

import by.travel.touristagency.util.HibernateTestUtil;
import by.travel.touristagency.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

class VoucherRepositoryTest {
    private static final SessionFactory SESSION_FACTORY = HibernateTestUtil.buildSessionFactory();
    private Session session;
    private VoucherRepository voucherRepository;

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
        voucherRepository = new VoucherRepository(session);
    }

    @AfterEach
    public void closeSession() {
        session.close();
    }

    @Test
    void findById() {

    }

    @Test
    void update() {

    }

    @Test
    void save() {

    }

    @Test
    void delete() {

    }

    @Test
    void getFilteredVoucher() {
    }

    @Test
    void getVoucherByAmountOfDays() {
    }

    @Test
    void getVouchersSortedByPriceASC() {
    }

    @Test
    void getVouchersSortedByPriceDESC() {
    }

    @Test
    void getVouchersSortedByFoodASC() {
    }

    @Test
    void getVouchersSortedByFoodDESC() {
    }
}