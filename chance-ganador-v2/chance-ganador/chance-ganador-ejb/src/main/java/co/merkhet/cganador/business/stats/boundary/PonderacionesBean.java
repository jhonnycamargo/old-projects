package co.merkhet.cganador.business.stats.boundary;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.merkhet.business.data.boundary.CrudService;
import co.merkhet.cganador.business.chance.entity.Loteria;
import co.merkhet.cganador.business.stats.entity.Ponderacion;

@Stateless
public class PonderacionesBean {

	@Inject
	private CrudService crudService;

	private String selectStatsQuery(Loteria loteria, Date fechaInicial,
			Date fechaFinal, int posicionInicial, int posicionFinal) {
		StringBuffer sb = new StringBuffer(
				"SELECT DISTINCT substr(s.RESULTADO, ?1, ?2) numero, cn.cuenta * 100 / t.TOTAL porcentaje, date_part('day', current_timestamp - d.LAST_DATE ) days ");
		sb.append("FROM SORTEO s, ").append(
				"(SELECT count(s1.SORTEO_ID) total FROM SORTEO s1 WHERE 1=1 ");
		if (loteria != null) {
			sb.append("AND s1.LOTERIA_ID = ?3 ");
		}
		if (fechaInicial != null) {
			sb.append("AND s1.FECHA >= ?4 ");
		}
		if (fechaFinal != null) {
			sb.append("AND s1.FECHA <= ?5 ");
		}
		sb.append(") as t, ")
				.append("(SELECT substr(s2.RESULTADO, ?1, ?2) n2, MAX(s2.FECHA) last_date FROM SORTEO s2 WHERE 1=1 ");
		if (loteria != null) {
			sb.append("AND s2.LOTERIA_ID = ?3 ");
		}
		if (fechaInicial != null) {
			sb.append("AND s2.FECHA >= ?4 ");
		}
		if (fechaFinal != null) {
			sb.append("AND s2.FECHA <= ?5 ");
		}
		sb.append("GROUP BY n2 ) d, ")
				.append("(SELECT substr(s3.RESULTADO, ?1, ?2) n3, count(s3.RESULTADO) cuenta FROM SORTEO s3 WHERE 1=1 ");
		if (loteria != null) {
			sb.append("AND s3.LOTERIA_ID = ?3 ");
		}
		if (fechaInicial != null) {
			sb.append("AND s3.FECHA >= ?4 ");
		}
		if (fechaFinal != null) {
			sb.append("AND s3.FECHA <= ?5 ");
		}
		sb.append("GROUP BY n3 ) cn ")
				.append("WHERE substr(s.RESULTADO, ?1, ?2) = d.n2 AND d.n2 = cn.n3 ")
				.append("ORDER BY days DESC");

		return sb.toString();
	}

	public List<Ponderacion> selectPonderacion(Loteria loteria,
			int posicionInicial, int posicionFinal) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT numero, (porcentaje * 0.65 + days * 0.35) ponderacion FROM ( ");
		sb.append(selectStatsQuery(loteria, null, null, posicionInicial,
				posicionFinal));
		sb.append(") totals");

		Map<Integer, Object> params = new HashMap<Integer, Object>();
		params.put(1, posicionInicial);
		params.put(2, posicionFinal);
		if (loteria != null) {
			params.put(3, loteria.getId());
		}
		return crudService.findByNativeQuery(sb.toString(),
				Ponderacion.PONDERACION_MAPPING, params);
	}
}
