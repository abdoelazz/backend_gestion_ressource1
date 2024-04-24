package gestionRessource.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "gestionRessource.backend")
public class BackendGestionRessourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendGestionRessourceApplication.class, args);
	}

}
