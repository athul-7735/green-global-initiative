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
 * Security configuration class for the application.
 * <p>
 * This class customizes Spring Security settings to manage authentication,
 * authorization, and request filtering. It is tailored for a development
 * environment, focusing on simplicity and flexibility while using JWT-based
 * authentication.
 * </p>
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *     <li>Disables CSRF and CORS for easier API testing and development.</li>
 *     <li>Allows all incoming requests without authentication.</li>
 *     <li>Integrates a custom JWT filter for token-based authentication.</li>
 *     <li>Disables default form login and HTTP Basic authentication.</li>
 * </ul>
 *
 * <p><b>Note:</b> This configuration is intended for development purposes
 * and should be adjusted for production environments with stricter security policies.</p>
 */

//@Configurable
@Configuration
@EnableWebSecurity
public class SecurityConfig {


    /**
     * Configures the security filter chain for the application.
     *
     * <p>This method sets up various security rules, including disabling CSRF
     * and CORS, permitting all requests, and adding a JWT filter for
     * token-based authentication.</p>
     *
     * @param http the {@link HttpSecurity} object to customize security configurations
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if there is an issue configuring the security filter chain
     */

//    @Autowired
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(authenticationProvider());
//
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(
//                        csrfConfigurer -> csrfConfigurer.csrfTokenRepository(new CookieCsrfTokenRepository()))
                                        AbstractHttpConfigurer::disable) // Disable CSRF protection for simplicity (not recommended for production APIs without additional security)
//                .csrf((csrf) -> csrf
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                        .csrfTokenRequestHandler(requestHandler)
//                )
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize
//                                .requestMatchers(HttpMethod.GET, "/api/applications").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/api/users/login").permitAll()
//                                .requestMatchers("/api/**").permitAll() // Public APIs
                                .anyRequest().permitAll()) // Allow all requests without authentication
//                            .requestMatchers(HttpMethod.GET, "/api/**").permitAll() // Allow all GET requests
//                            .requestMatchers(HttpMethod.POST, "/api/**").authenticated()) // Require authentication for POST
                            .formLogin(formLogin -> formLogin.disable()) // Disable the default login page
//                .httpBasic(httpBasic -> httpBasic.disable()); // Disable HTTP Basic authentication
                .httpBasic(httpBasic -> Customizer.withDefaults()); // Disable HTTP Basic authentication
        // Add a custom JWT filter before the UsernamePasswordAuthenticationFilter

                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection for simplicity (not recommended for production APIs without additional security)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/**").permitAll() // Public APIs
                        .requestMatchers("/actuator/**").permitAll()) // Public APIs
                        .formLogin(formLogin -> formLogin.disable()) // Disable the default login page
                .httpBasic(httpBasic -> httpBasic.disable()); // Disable HTTP Basic authentication

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    /**
     * Creates a bean for the custom JWT authentication filter.
     *
     * <p>The JWT filter intercepts requests, extracts tokens, and validates them
     * to authenticate users based on their JWTs.</p>
     *
     * @return an instance of {@link JwtFilter}
     */
    @Bean
    public JwtFilter jwtAuthenticationFilter() {
        return new JwtFilter();
    }

    // Commented-out code examples for additional features:
    // - Password encoding with BCryptPasswordEncoder
    // - Custom authentication provider setup
    // - CSRF configuration for enhanced security

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
