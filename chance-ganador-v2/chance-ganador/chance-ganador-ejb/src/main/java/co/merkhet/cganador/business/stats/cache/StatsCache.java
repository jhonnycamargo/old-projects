package co.merkhet.cganador.business.stats.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Singleton;
import javax.inject.Inject;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import co.merkhet.cganador.business.stats.boundary.StatsBean;
import co.merkhet.cganador.business.stats.entity.Stat;

@Singleton
public class StatsCache {

	@Inject
	private StatsBean statsBean;

	private static final Integer REFRESH_STATS_AFTER_5_SECONDS = 5;
	private static final Integer EXPIRE_STATS_AFTER_1_DAY = 1;
	private final LoadingCache<StatsCacheKey, Optional<List<Stat>>> cache;

	public StatsCache() {
		cache = CacheBuilder
				.newBuilder()
				.expireAfterWrite(EXPIRE_STATS_AFTER_1_DAY, TimeUnit.DAYS)
				.refreshAfterWrite(REFRESH_STATS_AFTER_5_SECONDS,
						TimeUnit.SECONDS)
				.build(new CacheLoader<StatsCacheKey, Optional<List<Stat>>>() {
					@Override
					public Optional<List<Stat>> load(StatsCacheKey statsCacheKey)
							throws Exception {
						return loadCache(statsCacheKey);
					}
				});
	}

	public Optional<List<Stat>> getEntry(StatsCacheKey statsCacheKey) {
		return cache.getUnchecked(statsCacheKey);
	}

	private Optional<List<Stat>> loadCache(StatsCacheKey statsCacheKey) {
		List<Stat> stats = statsBean.selectStats();
		return Optional.fromNullable(stats);
	}

}
