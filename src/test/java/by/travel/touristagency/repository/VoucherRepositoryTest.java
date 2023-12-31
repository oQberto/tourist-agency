package by.travel.touristagency.repository;

import by.travel.touristagency.dto.VoucherFilter;
import by.travel.touristagency.entity.Company;
import by.travel.touristagency.entity.Voucher;
import by.travel.touristagency.entity.enums.Country;
import by.travel.touristagency.entity.enums.Food;
import by.travel.touristagency.entity.enums.Transport;
import by.travel.touristagency.entity.enums.Type;
import by.travel.touristagency.util.HibernateTestUtil;
import by.travel.touristagency.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.SortDirection;
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
    void shouldFindAllVouchers() {
        session.beginTransaction();

        List<Voucher> actualResult = voucherRepository.getAll();
        assertThat(actualResult).hasSize(11);

        session.getTransaction().commit();
    }

    @Test
    void shouldFindVouchersByCompanyId() {
        session.beginTransaction();

        List<Voucher> actualResult = voucherRepository.getVouchersByCompanyId(2L);
        assertThat(actualResult).hasSize(3);

        List<String> voucherNames = actualResult.stream()
                .map(Voucher::getName)
                .toList();
        assertThat(voucherNames).contains("Voucher", "Voucher2", "Voucher8");

        session.getTransaction().commit();
    }

    @Test
    void shouldFindVoucherById() {
        session.beginTransaction();

        Optional<Voucher> actualResult = voucherRepository.findById(1L);

        assertThat(actualResult).isPresent();

        session.getTransaction().commit();
    }

    @Test
    void shouldNotFindVoucherIfDoesntExist() {
        session.beginTransaction();

        voucherRepository.delete(4L);
        Optional<Voucher> actualResult = voucherRepository.findById(4L);

        assertThat(actualResult).isEmpty();

        session.getTransaction().rollback();
    }

    @Test
    void updateExistingVoucher() {
        session.beginTransaction();

        Optional<Voucher> actualResult = voucherRepository.findById(5L);
        assertThat(actualResult).isPresent();

        Voucher voucher = actualResult.get();
        voucher.setPrice(0.0);
        voucher.setName("NewNameVoucher5");
        voucherRepository.update(voucher);

        Optional<Voucher> actualResultAfterUpdate = voucherRepository.findById(5L);
        assertThat(actualResultAfterUpdate).isPresent();
        assertThat(actualResultAfterUpdate.get()).isEqualTo(voucher);

        session.getTransaction().rollback();
    }

    @Test
    void shouldSaveIfVoucherDoesNotExist() {
        session.beginTransaction();

        Optional<Voucher> existingVoucher = voucherRepository.findById(1L);
        assertThat(existingVoucher).isPresent();

        Company company = existingVoucher.get().getCompany();
        Voucher voucher = Voucher.builder()
                .company(company)
                .name("New Voucher")
                .price(0.0)
                .type(Type.THERAPY)
                .description(null)
                .build();
        Voucher savedVoucher = voucherRepository.save(voucher);

        Optional<Voucher> actualResult = voucherRepository.findById(savedVoucher.getId());
        assertThat(actualResult).isPresent();

        session.getTransaction().rollback();
    }

    @Test
    void shouldDeleteIfVoucherExists() {
        session.beginTransaction();

        Optional<Voucher> actualResult = voucherRepository.findById(6L);
        assertThat(actualResult).isPresent();

        voucherRepository.delete(actualResult.get().getId());

        Optional<Voucher> voucherAfterDelete = voucherRepository.findById(6L);
        assertThat(voucherAfterDelete).isEmpty();

        session.getTransaction().rollback();
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
    void getFilteredVoucherByPrice() {
        session.beginTransaction();

        VoucherFilter voucherFilter = VoucherFilter.builder()
                .priceFrom(100.0)
                .priceTo(125.0)
                .build();

        List<Voucher> actualResult = voucherRepository.getFilteredVoucher(voucherFilter);
        assertThat(actualResult).hasSize(2);

        List<String> voucherNames = actualResult.stream().map(Voucher::getName).toList();
        assertThat(voucherNames).contains("Voucher", "Voucher10");

        session.getTransaction().commit();
    }

    @Test
    void shouldNotFindVouchersIfChosenPriceDoesNotExist() {
        session.beginTransaction();

        VoucherFilter voucherFilter = VoucherFilter.builder()
                .priceFrom(1000.0)
                .priceTo(1000.0)
                .build();

        List<Voucher> actualResult = voucherRepository.getFilteredVoucher(voucherFilter);
        assertThat(actualResult).hasSize(0);

        session.getTransaction().commit();
    }

    @Test
    void getVouchersByType() {
        session.beginTransaction();

        VoucherFilter voucherFilter = VoucherFilter.builder()
                .type(Type.SHOPPING)
                .build();

        List<Voucher> actualResult = voucherRepository.getFilteredVoucher(voucherFilter);
        assertThat(actualResult).hasSize(2);

        List<String> voucherNames = actualResult.stream()
                .map(Voucher::getName)
                .toList();
        assertThat(voucherNames).contains("Voucher1", "Voucher3");

        session.getTransaction().commit();
    }

    @Test
    void getVouchersByCountry() {
        session.beginTransaction();

        VoucherFilter voucherFilter = VoucherFilter.builder()
                .country(Country.PL)
                .build();

        List<Voucher> actualResult = voucherRepository.getFilteredVoucher(voucherFilter);
        assertThat(actualResult).hasSize(3);

        List<String> voucherNames = actualResult.stream()
                .map(Voucher::getName)
                .toList();
        assertThat(voucherNames).contains("Voucher", "Voucher7", "Voucher8");

        session.getTransaction().commit();
    }

    @Test
    void getVouchersByTransport() {
        session.beginTransaction();

        VoucherFilter voucherFilter = VoucherFilter.builder()
                .transport(Transport.BUS)
                .build();

        List<Voucher> actualResult = voucherRepository.getFilteredVoucher(voucherFilter);
        assertThat(actualResult).hasSize(6);

        List<String> voucherNames = actualResult.stream()
                .map(Voucher::getName)
                .toList();
        assertThat(voucherNames).contains("Voucher", "Voucher7", "Voucher4", "Voucher9", "Voucher3", "Voucher5");

        session.getTransaction().commit();
    }

    @Test
    void getVouchersByFood() {
        session.beginTransaction();

        VoucherFilter voucherFilter = VoucherFilter.builder()
                .food(Food.AI)
                .build();

        List<Voucher> actualResult = voucherRepository.getFilteredVoucher(voucherFilter);
        assertThat(actualResult).hasSize(3);

        List<String> voucherNames = actualResult.stream()
                .map(Voucher::getName)
                .toList();
        assertThat(voucherNames).contains("Voucher", "Voucher2", "Voucher9");

        session.getTransaction().commit();
    }

    @Test
    void getVouchersByFilter() {
        session.beginTransaction();

        VoucherFilter voucherFilter = VoucherFilter.builder()
                .priceFrom(125.0)
                .priceTo(139.0)
                .type(Type.SHOPPING)
                .transport(Transport.PLANE)
                .build();

        List<Voucher> actualResult = voucherRepository.getFilteredVoucher(voucherFilter);
        assertThat(actualResult).hasSize(1);

        List<String> voucherNames = actualResult.stream()
                .map(Voucher::getName)
                .toList();
        assertThat(voucherNames).contains("Voucher1");

        session.getTransaction().commit();
    }

    @Test
    void getVoucherByAmountOfDays() {
        session.beginTransaction();

        List<Voucher> actualResult = voucherRepository.getVouchersByAmountOfDays(10);
        assertThat(actualResult).hasSize(3);

        List<String> voucherNames = actualResult.stream()
                .map(Voucher::getName)
                .toList();
        assertThat(voucherNames).contains("Voucher9", "Voucher3", "Voucher4");

        session.getTransaction().commit();
    }

    @Test
    void getVouchersSortedByPriceASC() {
        session.beginTransaction();

        List<Voucher> actualResult = voucherRepository.getVouchersSortedByPrice(SortDirection.ASCENDING, 5);
        assertThat(actualResult).hasSize(5);

        List<String> voucherNames = actualResult.stream()
                .map(Voucher::getName)
                .toList();
        assertThat(voucherNames).contains("Voucher10", "Voucher", "Voucher8", "Voucher1", "Voucher9");

        session.getTransaction().commit();
    }

    @Test
    void getVouchersSortedByPriceDESC() {
        session.beginTransaction();

        List<Voucher> actualResult = voucherRepository.getVouchersSortedByPrice(SortDirection.DESCENDING, 5);
        assertThat(actualResult).hasSize(5);

        List<String> voucherNames = actualResult.stream()
                .map(Voucher::getName)
                .toList();
        assertThat(voucherNames).contains("Voucher7", "Voucher6", "Voucher5", "Voucher4", "Voucher3");

        session.getTransaction().commit();
    }
}