package tech.cognity.garage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiGarageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGarageApplication.class, args);
	}

}
