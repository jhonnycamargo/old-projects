package co.merkhet.trex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"co.merkhet.trex.repository"})
public class JpaConfiguration {

}
