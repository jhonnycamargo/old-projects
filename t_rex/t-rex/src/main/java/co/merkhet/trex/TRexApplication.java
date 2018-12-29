package co.merkhet.trex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = { TRexApplication.class, Jsr310JpaConverters.class })
public class TRexApplication {

	public static void main(String[] args) {
		SpringApplication.run(TRexApplication.class, args);
	}

}
