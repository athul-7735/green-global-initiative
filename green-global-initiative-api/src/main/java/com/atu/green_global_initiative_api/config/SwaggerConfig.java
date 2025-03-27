package com.atu.green_global_initiative_api.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Nausicaa Green Initiative API")
                        .description("API documentation for Nausicaa Global Green Initiative")
                        .version("1.0.0"));
    }
}
