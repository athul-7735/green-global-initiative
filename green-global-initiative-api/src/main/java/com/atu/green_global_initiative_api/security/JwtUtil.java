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
 * Utility class that handles operations related to JWT (JSON Web Token),
 * such as generating, extracting information, and validating tokens.
 * The class interacts with the JWT library to create secure tokens and
 * verify their validity.
 */
@Component
public class JwtUtil {

    /**
     * The secret key used for signing the JWT tokens.
     * This is fetched from the application environment properties.
     */
//    @Value("${jwt_secret}")
    private final String secret;

    /**
     * Constructor that initializes the JwtUtil with the secret key from environment.
     *
     * @param env the Environment object to fetch the secret key property.
     */
    public JwtUtil(Environment env) {
        this.secret = env.getProperty("jwt_secret");
    }

    /**
     * Generates a JWT token for the provided user details.
     * The token contains information such as user ID, first name, last name, email, and admin status.
     * The token is signed using the secret key and has an expiration of 10 hours.
     *
     * @param userDetailsDto the user details to be included in the token.
     * @return a signed JWT token as a string.
     * @throws IllegalArgumentException if there is an error during token generation.
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
     * Extracts the email from the given JWT token.
     * The email is retrieved from the claims of the token.
     *
     * @param token the JWT token from which the email is to be extracted.
     * @return the email associated with the JWT token.
     */
    public String extractEmail(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("email").toString();
    }

    /**
     * Validates the JWT token by checking if the token is valid for the given user details.
     * It checks that the username (email) in the token matches the provided user details and
     * verifies that the token is not expired.
     *
     * @param token the JWT token to be validated.
     * @param userDetailsDto the user details to compare with the token's email.
     * @return true if the token is valid for the user and not expired, false otherwise.
     */
    public boolean validateToken(String token, UserDetailsDto userDetailsDto) {
        final String username = extractEmail(token);
        return username.equals(userDetailsDto.getEmail()) && !isTokenExpired(token);
    }

    /**
     * Checks if the given JWT token has expired.
     * The token expiration time is compared with the current time.
     *
     * @param token the JWT token to be checked for expiration.
     * @return true if the token has expired, false otherwise.
     */
    private boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
