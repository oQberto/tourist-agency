package by.travel.touristagency.service;

import by.travel.touristagency.entity.Company;
import by.travel.touristagency.repository.CompanyRepository;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CompanyService {
    private static final CompanyService INSTANCE = new CompanyService();
    private final CompanyRepository companyRepository = new CompanyRepository(null);

    public List<Company> getAllCompanies(SessionFactory sessionFactory) {
        List<Company> companies;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            companyRepository.setSession(session);

            companies = companyRepository.getAll();

            session.getTransaction().commit();
        }
        return companies;
    }

    public static CompanyService getInstance() {
        return INSTANCE;
    }
}
