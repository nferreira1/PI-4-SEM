package br.edu.senac.senac;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "TechCommerce API",
				version = "v1",
				description = "API para o projeto TechCommerce"
		)
)
public class TechcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechcommerceApplication.class, args);
	}

}
