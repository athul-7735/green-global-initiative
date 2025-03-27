package com.atu.green_global_initiative_api.security;

import com.atu.green_global_initiative_api.dto.UserDetailsDto;
import com.atu.green_global_initiative_api.service.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

/**
 * A filter class that intercepts incoming HTTP requests to validate JWT tokens.
 * This filter ensures that the user is authenticated by verifying the JWT token
 * sent in the Authorization header.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    /**
     * Utility class to handle JWT token operations such as extraction and validation.
     */
//    @Autowired private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Service used to fetch user details from the database based on email.
     */
    @Autowired
    private UserServiceImpl userService;

    /**
     * This method is invoked for every request to validate the JWT token and
     * set the user authentication in the security context if the token is valid.
     *
     * @param request  the HttpServletRequest containing the incoming request.
     * @param response the HttpServletResponse to which the response should be written.
     * @param filterChain the filter chain to allow further processing after this filter.
     * @throws ServletException if a servlet-related error occurs.
     * @throws IOException if an I/O error occurs while processing the request.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        // Check if the Authorization header is present and starts with "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractEmail(jwt);
        }

        // If the username is not null and no authentication is present, validate the token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetailsDto userDetailsDto = this.userService.getAllUserDetailsByEmail(username);
            if (jwtUtil.validateToken(jwt, userDetailsDto)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetailsDto, null);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // Proceed to the next filter in the chain
        filterChain.doFilter(request, response);
    }
}
