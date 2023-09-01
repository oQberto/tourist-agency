package by.travel.touristagency.repository;

import by.travel.touristagency.entity.User;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

import java.util.Optional;

import static by.travel.touristagency.entity.QUser.user;

public class UserRepository extends BaseRepository<Long, User> {
    private static volatile UserRepository instance;

    private UserRepository(Class<User> clazz, Session session) {
        super(clazz, session);
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        return Optional.ofNullable(
                new JPAQuery<User>(getSession())
                        .select(user)
                        .from(user)
                        .where(user.email.eq(email), user.password.eq(password))
                        .fetchOne()
        );
    }

    public static UserRepository getInstance(Session session) {
        UserRepository result = instance;
        if (result != null) {
            return result;
        }

        synchronized (BookingRepository.class) {
            if (instance == null) {
                instance = new UserRepository(User.class, session);
            }
            return instance;
        }
    }
}
