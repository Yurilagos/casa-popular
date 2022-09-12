package br.com.dginx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableMongoRepositories
@EnableOpenApi //Enable open api 3.0.3 spec
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CasaPopularApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasaPopularApplication.class, args);
	}

}
