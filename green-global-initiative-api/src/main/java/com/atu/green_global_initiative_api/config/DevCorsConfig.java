package com.atu.green_global_initiative_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Development-specific CORS configuration class.
 * <p>
 * This class is activated only when the "development" profile is active. It provides
 * unrestricted Cross-Origin Resource Sharing (CORS) policies for API endpoints to
 * facilitate development and testing.
 * </p>
 *
 * <p>
 * Key Features:
 * <ul>
 *     <li>Applies CORS settings to all API endpoints.</li>
 *     <li>Allows requests from all origins.</li>
 *     <li>Provides flexibility for developers during the development phase.</li>
 * </ul>
 * </p>
 *
 * <p><b>Note:</b> This configuration is not intended for production use.</p>
 */

@Configuration
@Profile("development")
@EnableWebMvc

public class DevCorsConfig implements WebMvcConfigurer {

    /**
     * Configures CORS mappings for the application.
     *
     * <p>This method applies a CORS policy that allows unrestricted access to all endpoints
     * from any origin during development.</p>
     *
     * @param registry the {@link CorsRegistry} used to configure CORS mappings
     */


    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**")
//                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
////                .allowedOrigins("http://localhost:8080");

        // Apply the CORS policy for all endpoints, allowing requests from all origins.
        registry.addMapping("/**")
//                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
                .allowedOrigins("*");
    }
}
