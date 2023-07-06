package com.example.jwtpractice.service;

import com.example.jwtpractice.exception.UserUnauthorizedException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 1000 * 60 * 30; // 30 minutes

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isValidateToken(String token) throws UserUnauthorizedException {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            // Invalid JWT signature
            throw new UserUnauthorizedException("Invalid token signature");
        } catch (MalformedJwtException ex) {
            // Invalid JWT token
            throw new UserUnauthorizedException("Invalid token");
        } catch (ExpiredJwtException ex) {
            // Expired JWT token
            throw new UserUnauthorizedException("Expired token");
        } catch (UnsupportedJwtException ex) {
            // Unsupported JWT token
            throw new UserUnauthorizedException("Unsupported token");
        } catch (IllegalArgumentException ex) {
            // JWT claims string is empty or null
            throw new UserUnauthorizedException("Invalid token");
        }

    }
}

