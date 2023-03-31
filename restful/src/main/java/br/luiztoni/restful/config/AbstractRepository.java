package br.luiztoni.restful.config;

import java.util.List;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;

@Dependent
public abstract class AbstractRepository<T> {

	protected AbstractRepository() {
	}

	protected Class<T> clazz;

    @PersistenceContext(name = "jpa-unit")
    protected EntityManager manager;

    public AbstractRepository(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Transactional
    public void create(T object) {
        manager.persist(object);
    }

    public T read(long id) {
        return manager.find(clazz, id);
    }

    @Transactional
    public void update(T object) {
        manager.merge(object);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(long id) {
        T object = this.read(id);
        manager.remove(object);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(T object) {
        object = manager.merge(object);
        manager.remove(object);
    }

    @SuppressWarnings("unchecked")
    public List<T> index() {
        CriteriaQuery<Object> query = manager.getCriteriaBuilder().createQuery();
        query.select(query.from(clazz));
        return (List<T>) manager.createQuery(query).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> paginate(int total, int page) {
        CriteriaQuery<Object> query = manager.getCriteriaBuilder().createQuery();
        query.select(query.from(clazz));
        Query result = manager.createQuery(query);
        result.setFirstResult(page * total);
        result.setMaxResults(total);
        return (List<T>) result.getResultList();
    }

    public long count() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(clazz)));
        return manager.createQuery(query).getSingleResult();
    }
}
