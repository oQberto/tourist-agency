package by.travel.touristagency.repository;

import by.travel.touristagency.entity.Company;
import jakarta.persistence.EntityManager;

public class CompanyRepository extends BaseRepository <Long, Company>{

    public CompanyRepository(EntityManager entityManager) {
        super(Company.class, entityManager);
    }
}
