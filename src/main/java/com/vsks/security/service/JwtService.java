package com.vsks.security.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtService {

    @Value("${jwt.expiryInMillis}")
    long EXPIRY_IN_MILLIS;

    @Value("${jwt.secretKey}")
    String SECRET_KEY;

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername() + "|" + userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .issuer("VSK Solutions")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRY_IN_MILLIS))
                .signWith(getSigningKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException ex) {
            System.out.println("JWT expired");
        } catch (IllegalArgumentException ex) {
            System.out.println("Token is null, empty or only whitespace");
        } catch (MalformedJwtException ex) {
            System.out.println("JWT is invalid");
        } catch (UnsupportedJwtException ex) {
            System.out.println("JWT is not supported");
        } catch (SignatureException ex) {
            System.out.println("Signature validation failed");
        }
        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private SecretKey getSigningKey() {
        // Ensure the key is at least 256 bits (32 bytes) for HS256
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
