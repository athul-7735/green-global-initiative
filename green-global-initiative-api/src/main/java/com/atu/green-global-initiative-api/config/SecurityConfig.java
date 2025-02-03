package com.atu.devops.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

//@Configurable
@Configuration
@EnableWebSecurity
public class SecurityConfig {

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
//        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
//        // set the name of the attribute the CsrfToken will be populated on
//        requestHandler.setCsrfRequestAttributeName(null);
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
        return http.build();
    }
}
