package com.atu.green_global_initiative_api.config;

import com.atu.green_global_initiative_api.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

/**
 * Configuration class for Spring Security settings.
 * This class sets up the security filter chain, JWT authentication filter, and CORS configurations.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /**
     * Configures the security filter chain.
     *
     * <p>This method sets up:
     * <ul>
     *     <li>CSRF protection (disabled for simplicity, but should be enabled in production with additional security).</li>
     *     <li>Authorization rules for public APIs.</li>
     *     <li>Disabling of default login page and HTTP Basic authentication.</li>
     *     <li>Integration of a custom JWT authentication filter.</li>
     * </ul>
     *
     * @param http the {@link HttpSecurity} object to configure security settings.
     * @return the configured {@link SecurityFilterChain}.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection for simplicity (not recommended for production APIs without additional security)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/**").permitAll() // Public APIs
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()) // Public APIs
                        .formLogin(formLogin -> formLogin.disable()) // Disable the default login page
                .httpBasic(httpBasic -> httpBasic.disable()); // Disable HTTP Basic authentication
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    /**
     * Creates and returns the JWT authentication filter bean.
     *
     * <p>The {@link JwtFilter} intercepts requests to validate JWT tokens for authentication.
     *
     * @return a new instance of {@link JwtFilter}.
     */
    @Bean
    public JwtFilter jwtAuthenticationFilter() {
        return new JwtFilter();
    }
    /**
     * Configures CORS settings for the application.
     *
     * <p>This method allows all origins, specific HTTP methods, and headers for cross-origin requests.
     * It also enables credentials for cross-origin requests.
     *
     * @return a {@link CorsConfigurationSource} containing the configured CORS rules.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));  // Set allowed origins
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
