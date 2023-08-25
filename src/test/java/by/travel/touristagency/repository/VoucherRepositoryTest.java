package by.travel.touristagency.repository;

import by.travel.touristagency.dto.VoucherFilter;
import by.travel.touristagency.entity.Company;
import by.travel.touristagency.entity.User;
import by.travel.touristagency.entity.Voucher;
import by.travel.touristagency.entity.enums.Type;
import by.travel.touristagency.util.HibernateTestUtil;
import by.travel.touristagency.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
    void shouldFindVoucherById() {
        session.beginTransaction();

        Optional<Voucher> actualResult = voucherRepository.findById(1L);

        assertThat(actualResult).isPresent();
        assertThat(actualResult.get().getUser().getEmail()).isEqualTo("uname@gmail.com");

        session.getTransaction().commit();
    }

    @Test
    void shouldNotFindVoucherIfDoesntExist() {
        session.beginTransaction();

        voucherRepository.delete(4L);
        Optional<Voucher> actualResult = voucherRepository.findById(4L);

        assertThat(actualResult).isEmpty();

        session.getTransaction().commit();
    }

    @Test
    void updateExistingVoucher() {
        session.beginTransaction();

        Optional<Voucher> actualResult = voucherRepository.findById(5L);
        assertThat(actualResult).isPresent();

        Voucher voucher = actualResult.get();
        voucher.setPrice(0.0);
        voucher.setName(null);
        voucherRepository.update(voucher);

        Optional<Voucher> actualResultAfterUpdate = voucherRepository.findById(5L);
        assertThat(actualResultAfterUpdate).isPresent();
        assertThat(actualResultAfterUpdate.get()).isEqualTo(voucher);

        session.getTransaction().commit();
    }

    @Test
    void shouldSaveIfVoucherDoesNotExist() {
        session.beginTransaction();

        Optional<Voucher> existingVoucher = voucherRepository.findById(1L);
        assertThat(existingVoucher).isPresent();

        User user = existingVoucher.get().getUser();
        Company company = existingVoucher.get().getCompany();
        Voucher voucher = Voucher.builder()
                .user(user)
                .company(company)
                .name("New Voucher")
                .price(0.0)
                .type(Type.THERAPY)
                .description(null)
                .build();
        Voucher savedVoucher = voucherRepository.save(voucher);

        Optional<Voucher> actualResult = voucherRepository.findById(savedVoucher.getId());
        assertThat(actualResult).isPresent();
        assertThat(actualResult.get().getUser().getEmail()).isEqualTo("uname@gmail.com");

        session.getTransaction().commit();
    }

    @Test
    void shouldDeleteIfVoucherExists() {
        session.beginTransaction();

        Optional<Voucher> actualResult = voucherRepository.findById(6L);
        assertThat(actualResult).isPresent();

        voucherRepository.delete(actualResult.get().getId());

        Optional<Voucher> voucherAfterDelete = voucherRepository.findById(6L);
        assertThat(voucherAfterDelete).isEmpty();

        session.getTransaction().commit();
    }

    @Test
    void getFilteredVoucherByName() {
        session.beginTransaction();

        Optional<Voucher> existingVoucher = voucherRepository.findById(2L);
        assertThat(existingVoucher).isPresent();

        VoucherFilter voucherFilter = VoucherFilter.builder()
                .name(existingVoucher.get().getName())
                .build();

        List<Voucher> actualResult = voucherRepository.getFilteredVoucher(voucherFilter);
        assertThat(actualResult).hasSize(1);

        List<String> voucherNames = actualResult.stream().map(Voucher::getName).toList();
        assertThat(voucherNames).contains("Voucher1");

        session.getTransaction().commit();
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