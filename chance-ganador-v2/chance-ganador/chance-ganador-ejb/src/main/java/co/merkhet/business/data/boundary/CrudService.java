package co.merkhet.business.data.boundary;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class CrudService {

	@PersistenceContext
	EntityManager em;

	public <T> T create(T t) {
		this.em.persist(t);
		this.em.flush();
		this.em.refresh(t);

		return t;
	}

	public <T> T find(Class<T> type, Object id) {
		return (T) this.em.find(type, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void delete(Class type, Object id) {
		Object ref = this.em.getReference(type, id);
		this.em.remove(ref);
	}

	public <T> T update(T t) {
		return (T) this.em.merge(t);
	}

	@SuppressWarnings("rawtypes")
	public List findWithNamedQuery(String namedQueryName,
			Map<String, Object> parameters) {
		return findWithNamedQuery(namedQueryName, parameters, 0);
	}

	@SuppressWarnings("rawtypes")
	public List findWithNamedQuery(String queryName, int resultLimit) {
		return this.em.createNamedQuery(queryName).setMaxResults(resultLimit)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findByNativeQuery(String sql, Class<T> type) {
		return this.em.createNativeQuery(sql, type).getResultList();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findByNativeQuery(String sql, String mapping) {
		return this.em.createNativeQuery(sql, mapping).getResultList();
	}

	public <T> List<T> findByNativeQuery(String sql, String mapping,
			Map<Integer, Object> parameters) {
		return findByNativeQuery(sql, mapping, parameters, 0);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findByNativeQuery(String sql, String mapping,
			Map<Integer, Object> parameters, int resultLimit) {
		Query query = em.createNativeQuery(sql, mapping);
		if (resultLimit > 0) {
			query.setMaxResults(resultLimit);
		}
		Set<Entry<Integer, Object>> rawParameters = parameters.entrySet();
		for (Entry<Integer, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query.getResultList();
	}

	@SuppressWarnings("rawtypes")
	public List findWithNamedQuery(String namedQueryName,
			Map<String, Object> parameters, int resultLimit) {
		Set<Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = this.em.createNamedQuery(namedQueryName);
		if (resultLimit > 0) {
			query.setMaxResults(resultLimit);
		}
		for (Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}

	@SuppressWarnings("rawtypes")
	public List findWithNamedQuery(String queryName, int pageNumber,
			int pageSize) {
		Query query = em.createNamedQuery(queryName);
		query.setFirstResult((pageNumber) * pageSize);
		query.setMaxResults(pageSize);

		return query.getResultList();
	}

	public long count(String queryName) {
		Query total = em.createNamedQuery(queryName);

		return (long) total.getSingleResult();
	}
}
