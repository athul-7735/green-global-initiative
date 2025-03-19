package com.atu.green_global_initiative_api.security;

import com.atu.green_global_initiative_api.dto.UserDetailsDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtUtil is a utility class for handling JWT operations such as token generation,
 * token validation, and extraction of user information from the token.
 */
@Component
public class JwtUtil {
//    @Value("${jwt_secret}")
    // Secret key for signing and validating JWT tokens, fetched from environment variables
    private final String secret;
    /**
     * Constructs a JwtUtil object and initializes the secret key.
     *
     * @param env the Spring Environment to access application properties
     */
    public JwtUtil(Environment env) {
        this.secret = env.getProperty("jwt_secret");
    }

    /**
     * Generates a JWT token for the given user details.
     * The token contains the user's ID, name, email, and admin status.
     *
     * @param userDetailsDto the user details to be included in the token
     * @return the generated JWT token
     * @throws IllegalArgumentException if the userDetailsDto is null or invalid
     */
    public String generateToken(UserDetailsDto userDetailsDto) throws IllegalArgumentException {
        Map<String, Object> userObject = new HashMap<>();
        userObject.put("id", userDetailsDto.getUserId());
        userObject.put("firstName", userDetailsDto.getFirstName());
        userObject.put("lastName", userDetailsDto.getLastName());
        userObject.put("email", userDetailsDto.getEmail());
        userObject.put("admin", userDetailsDto.getIsAdmin());
        return Jwts.builder()
                .setSubject(userDetailsDto.getEmail())
                .setClaims(userObject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    /**
     * Extracts the email address of the user from the JWT token.
     *
     * @param token the JWT token from which to extract the email
     * @return the email address extracted from the token
     */
    public String extractEmail(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("email").toString();
    }
    /**
     * Validates the JWT token by checking if the email in the token matches the user's email
     * and if the token is not expired.
     *
     * @param token          the JWT token to be validated
     * @param userDetailsDto the user details to validate against
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String token, UserDetailsDto userDetailsDto) {
        final String username = extractEmail(token);
        return username.equals(userDetailsDto.getEmail()) && !isTokenExpired(token);
    }
    /**
     * Checks if the JWT token has expired.
     *
     * @param token the JWT token to be checked for expiration
     * @return true if the token is expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
