package by.travel.touristagency.service;

import by.travel.touristagency.entity.Company;
import by.travel.touristagency.util.HibernateTestUtil;
import by.travel.touristagency.util.TestDataImporter;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyServiceTest {
    private static final SessionFactory SESSION_FACTORY = HibernateTestUtil.buildSessionFactory();
    private final CompanyService companyService = new CompanyService(SESSION_FACTORY);

    @BeforeAll
    public static void setUp() {
        TestDataImporter.importData(SESSION_FACTORY);
    }

    @Test
    void getAllCompanies() {
        List<Company> actualResult = companyService.getAllCompanies();
        assertThat(actualResult).hasSize(4);

        List<String> companyNames = actualResult.stream()
                .map(Company::getName)
                .toList();
        assertThat(companyNames).contains("Spain Agency", "Greece Agency", "Brazil Agency", "Belarus Agency");
    }
}