package by.travel.touristagency.repository;

import by.travel.touristagency.entity.User;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;

import java.util.Optional;

import static by.travel.touristagency.entity.QUser.user;

public class UserRepository extends BaseRepository<Long, User> {

    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        return Optional.ofNullable(
                new JPAQuery<User>(getEntityManager())
                        .select(user)
                        .from(user)
                        .where(user.email.eq(email), user.password.eq(password))
                        .fetchOne()
        );
    }
}
