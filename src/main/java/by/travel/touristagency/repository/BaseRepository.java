package by.travel.touristagency.repository;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BaseRepository<K extends Serializable, E> implements Repository<K, E> {
    private final Class<E> clazz;

    @Setter
    private EntityManager entityManager;

    @Override
    public List<E> getAll() {
        var criteria = entityManager
                .getCriteriaBuilder()
                .createQuery(clazz);
        criteria.from(clazz);

        return entityManager
                .createQuery(criteria)
                .getResultList();
    }

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
        entityManager.remove(deletableEntity);
    }
}
