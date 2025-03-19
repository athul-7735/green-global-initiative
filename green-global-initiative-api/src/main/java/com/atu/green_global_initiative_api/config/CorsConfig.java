package com.atu.green_global_initiative_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for CORS (Cross-Origin Resource Sharing) settings.
 *
 * <p>This configuration is active only in the "local" profile. It enables CORS for all endpoints
 * with unrestricted origins to simplify local development.
 */

@Configuration
@Profile("local")
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configures CORS mappings for the application.
     *
     * <p>This method allows all origins to access all endpoints. This setup is intended for use in
     * local development environments and should be modified or restricted for production environments
     * to ensure security.
     *
     * @param registry the {@link CorsRegistry} to configure CORS rules.
     */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*");
    }
}
