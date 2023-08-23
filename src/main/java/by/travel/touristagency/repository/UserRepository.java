package by.travel.touristagency.repository;

import by.travel.touristagency.entity.User;
import jakarta.persistence.EntityManager;

public class UserRepository extends BaseRepository<Long, User> {

    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
