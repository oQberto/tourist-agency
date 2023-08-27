package by.travel.touristagency.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<K, E> {

    List<E> getAll();

    Optional<E> findById(K id);

    void update(E entity);

    E save(E entity);

    void delete(K id);
}
