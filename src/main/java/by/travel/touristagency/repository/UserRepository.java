package by.travel.touristagency.repository;

import by.travel.touristagency.entity.User;
import jakarta.persistence.EntityManager;

public class UserRepository extends BaseRepository<Long, User> {

    public UserRepository(Class<User> clazz, EntityManager entityManager) {
        super(clazz, entityManager);
    }
}
