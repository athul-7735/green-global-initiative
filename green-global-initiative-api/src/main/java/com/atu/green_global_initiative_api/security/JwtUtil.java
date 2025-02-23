package com.atu.green_global_initiative_api.security;

import com.atu.green_global_initiative_api.dto.UserDetailsDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
//    @Value("${jwt_secret}")
    private final String secret;

    public JwtUtil(Environment env) {
        this.secret = env.getProperty("jwt_secret");
    }

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

    public String extractEmail(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("email").toString();
    }

    public boolean validateToken(String token, UserDetailsDto userDetailsDto) {
        final String username = extractEmail(token);
        return username.equals(userDetailsDto.getEmail()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
