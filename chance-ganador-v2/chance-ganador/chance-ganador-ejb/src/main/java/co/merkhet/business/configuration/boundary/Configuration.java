package co.merkhet.business.configuration.boundary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import co.merkhet.business.configuration.control.ConfigurationProvider;

/**
 * User: blog.adam-bien.com Date: 14.02.11 Time: 19:06
 */
@Startup
@Singleton
public class Configuration {

	private Map<String, String> configuration;
	private Set<String> unconfiguredFields;

	@Inject
	private Instance<ConfigurationProvider> configurationProvider;

	@PostConstruct
	public void fetchConfiguration() {
		this.configuration = new HashMap<String, String>() {
			{
				put("version", "0.9");
				put("hitsFlushRate", "1");
				put("referersFlushRate", "1");
				put("scavengerRuns", "Mon,Wed,Fri");
				put("debug", "false");
			}
		};
		this.unconfiguredFields = new HashSet<String>();
		mergeWithCustomConfiguration();
	}

	public boolean doesCustomConfigurationExist() {
		return !configurationProvider.isUnsatisfied();
	}

	void mergeWithCustomConfiguration() {
		for (ConfigurationProvider provider : configurationProvider) {
			Map<String, String> customConfiguration = provider
					.getConfiguration();
			this.configuration.putAll(customConfiguration);
		}
	}

	@javax.enterprise.inject.Produces
	public String getString(InjectionPoint point) {
		String fieldName = point.getMember().getName();
		String valueForFieldName = configuration.get(fieldName);
		if (valueForFieldName == null) {
			this.unconfiguredFields.add(fieldName);
		}
		return valueForFieldName;
	}

	@javax.enterprise.inject.Produces
	public long getLong(InjectionPoint point) {
		String stringValue = getString(point);
		if (stringValue == null) {
			return 0;
		}
		return Long.parseLong(stringValue);
	}

	@javax.enterprise.inject.Produces
	public int getInteger(InjectionPoint point) {
		String stringValue = getString(point);
		if (stringValue == null) {
			return 0;
		}
		return Integer.parseInt(stringValue);
	}

	@javax.enterprise.inject.Produces
	public boolean getBoolean(InjectionPoint point) {
		String stringValue = getString(point);
		if (stringValue == null) {
			return false;
		}
		return Boolean.parseBoolean(stringValue);
	}

	public Set<String> getUnconfiguredFields() {
		return this.unconfiguredFields;
	}

	public void debugEnabled() {
		this.configuration.put("debug", Boolean.TRUE.toString());
	}

	public void debugDisabled() {
		this.configuration.put("debug", Boolean.FALSE.toString());
	}
}
