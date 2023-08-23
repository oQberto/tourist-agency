package by.travel.touristagency.repository;

import by.travel.touristagency.entity.User;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserRepository implements BaseRepository<Long, User> {
    private static final UserRepository INSTANCE = new UserRepository();

    @Override
    public Optional<User> findById(Session session, Long id) {
        return Optional.ofNullable(session.get(User.class, id));
    }

    @Override
    public void update(Session session, User entity) {
        session.merge(entity);
    }

    @Override
    public User save(Session session, User entity) {
        session.persist(entity);
        return entity;
    }

    @Override
    public void delete(Session session, Long id) {
        User deletableUser = session.get(User.class, id);
        session.remove(deletableUser);
    }

    public static UserRepository getInstance() {
        return INSTANCE;
    }
}
