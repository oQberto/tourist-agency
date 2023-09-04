package by.travel.touristagency.repository;

import by.travel.touristagency.entity.Company;
import lombok.Generated;
import org.hibernate.Session;

public class CompanyRepository extends BaseRepository <Long, Company>{

    @Generated
    public CompanyRepository(Session session) {
        super(Company.class, session);
    }
}
