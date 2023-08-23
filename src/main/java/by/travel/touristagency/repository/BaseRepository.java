package by.travel.touristagency.repository;

import jakarta.persistence.EntityManager;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

@Data
@RequiredArgsConstructor
public class BaseRepository<K extends Serializable, E> implements Repository<K, E>{
    private final Class<E> clazz;
    private final EntityManager entityManager;

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
        E deletableEntity = entityManager.find(clazz, id);
        entityManager.refresh(deletableEntity);
    }
}
