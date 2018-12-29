package co.merkhet.cganador.business.chance.boundary;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import co.merkhet.business.data.boundary.CrudService;
import co.merkhet.cganador.business.chance.entity.Loteria;

@Stateless
@Named
public class LoteriasBean {

	@Inject
	private CrudService crudService;

	@SuppressWarnings("unchecked")
	public List<Loteria> selectAllLoteries() {
		return (List<Loteria>) crudService.findWithNamedQuery(Loteria.FIND_ALL,
				Collections.EMPTY_MAP);
	}
}
