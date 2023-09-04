package by.travel.touristagency.service;

import by.travel.touristagency.entity.Company;
import by.travel.touristagency.repository.CompanyRepository;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CompanyService {
    private static volatile CompanyService instance;
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies(SessionFactory sessionFactory) {
        List<Company> companies;
        try (Session session = sessionFactory.openSession()) {
            companyRepository = new CompanyRepository(session);
            session.beginTransaction();

            companies = companyRepository.getAll();

            session.getTransaction().commit();
        }
        return companies;
    }

    @Generated
    public static CompanyService getInstance() {
        CompanyService result = instance;
        if (result != null) {
            return result;
        }

        synchronized (CompanyService.class) {
            if (instance == null) {
                instance = new CompanyService();
            }
            return instance;
        }
    }
}
