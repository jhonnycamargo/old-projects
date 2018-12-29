package co.merkhet.cganador.business.chance.boundary;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import co.merkhet.business.data.boundary.CrudService;
import co.merkhet.cganador.business.chance.entity.Loteria;
import co.merkhet.cganador.business.chance.entity.Sorteo;

@Stateless
@Named
public class Sorteos {

	@Inject
	private CrudService crudService;
	@Inject
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Sorteo> selectSorteosWithLimit(int pageNumber, int pageSize) {
		return (List<Sorteo>) crudService.findWithNamedQuery(Sorteo.FIND_ALL,
				pageNumber, pageSize);
	}

	public long getSorteosCount() {
		return crudService.count(Sorteo.COUNT_ALL);
	}

	public List<Sorteo> selectSorteosWithLimit(Loteria loteria, Date fechaIni,
			Date fechaFin, int pageNumber, int pageSize) {
		CriteriaBuilder builder = this.em.getCriteriaBuilder();
		CriteriaQuery<Sorteo> criteriaQuery = builder.createQuery(Sorteo.class);
		Root<Sorteo> root = criteriaQuery.from(Sorteo.class);
		TypedQuery<Sorteo> query = this.em.createQuery(criteriaQuery
				.select(root)
				.where(getSorteosSearchPredicates(root, loteria, fechaIni,
						fechaFin)).orderBy(builder.desc(root.get("fecha"))));
		query.setFirstResult(pageNumber * pageSize).setMaxResults(pageSize);

		return query.getResultList();
	}

	public long selectCountSorteosWithLimit(Loteria loteria, Date fechaIni,
			Date fechaFin, int pageNumber, int pageSize) {
		CriteriaBuilder builder = this.em.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		Root<Sorteo> root = criteriaQuery.from(Sorteo.class);
		criteriaQuery = criteriaQuery.select(builder.count(root)).where(
				getSorteosSearchPredicates(root, loteria, fechaIni, fechaFin));

		return this.em.createQuery(criteriaQuery).getSingleResult();
	}

	private Predicate[] getSorteosSearchPredicates(Root<Sorteo> root,
			Loteria loteria, Date fechaIni, Date fechaFin) {
		CriteriaBuilder builder = this.em.getCriteriaBuilder();
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (loteria != null) {
			predicates.add(builder.equal(root.get("loteria"), loteria));
		}
		if (fechaIni != null) {
			predicates.add(builder.greaterThanOrEqualTo(
					root.<Date> get("fecha"), fechaIni));
		}
		if (fechaFin != null) {
			predicates.add(builder.lessThanOrEqualTo(root.<Date> get("fecha"),
					fechaFin));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@SuppressWarnings("unchecked")
	public List<Sorteo> findAll(Loteria loteria) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loteria", loteria);
		return crudService
				.findWithNamedQuery(Sorteo.FIND_ALL_BY_LOTERY, params);
	}
}
