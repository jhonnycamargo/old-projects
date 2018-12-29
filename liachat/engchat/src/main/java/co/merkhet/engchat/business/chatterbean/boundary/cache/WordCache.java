package co.merkhet.engchat.business.chatterbean.boundary.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

import co.merkhet.engchat.business.chat.entity.Word;

@Singleton
@Startup
public class WordCache {

	LoadingCache<String, Word> cache;

	@Inject
	WordCacheLoader loader;

	// @Inject
	// WordRemovalListener listener;

	@PostConstruct
	public void init() {
		cache = CacheBuilder.newBuilder().maximumSize(100000).expireAfterAccess(10, TimeUnit.SECONDS).build(loader);
	}

	public Word get(String key) throws ExecutionException {
		return cache.get(key);
	}
}
