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
 * JwtFilter is a custom filter used to authenticate JWT tokens in HTTP requests.
 * It intercepts the request and validates the JWT token if present in the Authorization header.
 * If the token is valid, it sets the authentication context for the request.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

// Injected JwtUtil to handle JWT operations such as token extraction and validation
//  @Autowired private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    // Injected UserServiceImpl to fetch user details based on email
    @Autowired
    private UserServiceImpl userService;

    /**
     * Filters incoming HTTP requests to authenticate the user based on the JWT token.
     * If the token is valid, the user's details are fetched and authentication context is set.
     *
     * @param request     the HTTP request being processed
     * @param response    the HTTP response to be sent
     * @param filterChain the filter chain that allows further filtering of the request
     * @throws ServletException if an error occurs during filtering
     * @throws IOException      if an I/O error occurs during filtering
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        // Check if the Authorization header is present and contains a Bearer token
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractEmail(jwt);
        }

        // If username is found and the authentication context is not set, validate the token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Fetch user details by email from the UserService
            UserDetailsDto userDetailsDto = this.userService.getAllUserDetailsByEmail(username);
            // If the token is valid, set the authentication context
            if (jwtUtil.validateToken(jwt, userDetailsDto)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetailsDto, null);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
