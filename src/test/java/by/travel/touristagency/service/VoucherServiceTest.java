package by.travel.touristagency.service;

import by.travel.touristagency.entity.Voucher;
import by.travel.touristagency.util.HibernateTestUtil;
import by.travel.touristagency.util.TestDataImporter;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class VoucherServiceTest {
    private static final SessionFactory SESSION_FACTORY = HibernateTestUtil.buildSessionFactory();
    private VoucherService voucherService = new VoucherService(SESSION_FACTORY);

    @BeforeAll
    public static void setUp() {
        TestDataImporter.importData(SESSION_FACTORY);
    }

    @Test
    void getVouchersByCompanyId() {
        Long companyId = 2L;

        List<Voucher> actualResult = voucherService.getVouchersByCompanyId(companyId);
        assertThat(actualResult).hasSize(3);

        List<String> voucherNames = actualResult.stream()
                .map(Voucher::getName)
                .toList();
        assertThat(voucherNames).contains("Voucher", "Voucher2", "Voucher8");
    }

    @Test
    void getVoucherById() {
        Long voucherId = 2L;

        Optional<Voucher> actualResult = voucherService.getVoucherById(voucherId);

        assertThat(actualResult).isPresent();
        assertThat(actualResult.get().getName()).isEqualTo("Voucher1");
    }
}