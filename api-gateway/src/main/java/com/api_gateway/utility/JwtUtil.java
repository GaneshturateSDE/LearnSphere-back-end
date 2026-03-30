package com.api_gateway.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "!@#$%^&*()!@#$%^&*()!@#$%^&*()!@#$%^&*()";
    private static final long EXPIRATION = 1000 * 60 * 60 * 24; // 1 day

    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());


    public static String extractUserId(String token) {
        return validateToken(token).getSubject(); // store userId as subject
    }

    public static Claims validateToken(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
