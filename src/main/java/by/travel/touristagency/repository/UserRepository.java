package by.travel.touristagency.repository;

import by.travel.touristagency.entity.User;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.Generated;
import org.hibernate.Session;

import java.util.Optional;

import static by.travel.touristagency.entity.QUser.user;

public class UserRepository extends BaseRepository<Long, User> {

    @Generated
    public UserRepository(Session session) {
        super(User.class, session);
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
}
