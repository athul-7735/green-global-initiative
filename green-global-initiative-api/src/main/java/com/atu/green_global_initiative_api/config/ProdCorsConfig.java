package com.atu.green_global_initiative_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for CORS (Cross-Origin Resource Sharing) settings in the production environment.
 *
 * <p>This configuration ensures that CORS rules are applied only in the "production" profile. It allows specific
 * HTTP methods and enables cross-origin requests with credentials for endpoints under the "/api/**" path.</p>
 */

@Configuration
@Profile("production")
@EnableWebMvc
public class ProdCorsConfig implements WebMvcConfigurer {
    /**
     * Configures CORS mappings for the application in the production environment.
     *
     * <p>This method applies CORS rules to all endpoints matching the "/api/**" path pattern. It allows the
     * following configurations:
     * <ul>
     *     <li>Permitted HTTP methods: GET, POST, PUT, PATCH, DELETE, OPTIONS.</li>
     *     <li>Allows all origins ("*").</li>
     *     <li>Allows all headers ("*").</li>
     *     <li>Enables credentials for cross-origin requests.</li>
     * </ul>
     *
     * @param registry the {@link CorsRegistry} to configure CORS rules.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/api/**")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
