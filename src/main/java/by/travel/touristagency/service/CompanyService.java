package by.travel.touristagency.service;

import by.travel.touristagency.entity.Company;
import by.travel.touristagency.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@RequiredArgsConstructor
public class CompanyService {
    private final SessionFactory sessionFactory;
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        List<Company> companies;
        try (Session session = sessionFactory.openSession()) {
            companyRepository = new CompanyRepository(session);
            session.beginTransaction();

            companies = companyRepository.getAll();

            session.getTransaction().commit();
        }
        return companies;
    }
}
