package by.travel.touristagency.repository;

import by.travel.touristagency.entity.Profile;
import jakarta.persistence.EntityManager;

public class ProfileRepository extends BaseRepository<Long, Profile> {

    public ProfileRepository(EntityManager entityManager) {
        super(Profile.class, entityManager);
    }
}
