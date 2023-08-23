package by.travel.touristagency.repository;

import org.hibernate.Session;

import java.util.Optional;

public interface BaseRepository<K, E> {


    Optional<E> findById(Session session, K id);

    void update(Session session, E entity);

    E save(Session session, E entity);

    void delete(Session session, K id);
}
