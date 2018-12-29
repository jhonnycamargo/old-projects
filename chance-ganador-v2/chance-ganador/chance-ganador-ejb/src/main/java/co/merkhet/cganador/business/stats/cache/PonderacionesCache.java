package co.merkhet.cganador.business.stats.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Singleton;
import javax.inject.Inject;

import co.merkhet.cganador.business.stats.boundary.PonderacionesBean;
import co.merkhet.cganador.business.stats.entity.Ponderacion;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Singleton
public class PonderacionesCache {

	@Inject
	private PonderacionesBean ponderacionesBean;

	private static final Integer REFRESH_PONDERACION_AFTER_X_HOURS = 5;
	private static final Integer EXPIRE_PONDERACION_AFTER_1_DAY = 1;
	private final LoadingCache<PonderacionCacheKey, Optional<List<Ponderacion>>> cache;

	public PonderacionesCache() {
		cache = CacheBuilder
				.newBuilder()
				.expireAfterWrite(EXPIRE_PONDERACION_AFTER_1_DAY, TimeUnit.DAYS)
				.refreshAfterWrite(REFRESH_PONDERACION_AFTER_X_HOURS,
						TimeUnit.HOURS)
				.build(new CacheLoader<PonderacionCacheKey, Optional<List<Ponderacion>>>() {
					@Override
					public Optional<List<Ponderacion>> load(
							PonderacionCacheKey ponderacionCacheKey)
							throws Exception {
						return loadCache(ponderacionCacheKey);
					}
				});
	}

	public Optional<List<Ponderacion>> getEntry(
			PonderacionCacheKey ponderacionCacheKey) {
		return cache.getUnchecked(ponderacionCacheKey);
	}

	private Optional<List<Ponderacion>> loadCache(
			PonderacionCacheKey ponderacionCacheKey) {
		List<Ponderacion> ponderaciones = ponderacionesBean.selectPonderacion(
				ponderacionCacheKey.getLoteria(),
				ponderacionCacheKey.getPosicionInicial(),
				ponderacionCacheKey.getPosicionFinal());
		return Optional.fromNullable(ponderaciones);
	}

}
