package br.edu.senac.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI swagger() {
        return new OpenAPI()
                .info(new Info().title("TechCommerce API")
                        .version("1.0")
                        .description("API para o sistema de e-commerce TechCommerce."));
    }
}
