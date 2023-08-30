package by.travel.touristagency.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BaseRepository<K extends Serializable, E> implements Repository<K, E> {
    private final Class<E> clazz;

    @Setter
    private Session session;

    @Override
    public List<E> getAll() {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<E> root = criteriaQuery.from(clazz);

        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));

        return session
                .createQuery(criteriaQuery)
                .getResultList();
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(session.find(clazz, id));
    }

    @Override
    public void update(E entity) {
        session.merge(entity);
    }

    @Override
    public E save(E entity) {
        session.persist(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
        E deletableEntity = session.find(clazz, id);
        session.remove(deletableEntity);
    }
}
