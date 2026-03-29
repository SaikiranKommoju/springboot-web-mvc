package com.vsks.controller;

import com.vsks.security.auth.TokenResponse;
import com.vsks.security.principal.LocalUserDetails;
import com.vsks.security.service.JwtService;
import com.vsks.security.service.UserAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class TokenController {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Value("${jwt.expiryInMillis}")
    long tokenExpiryInMillis;

    @PostMapping(value = "/token", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestHeader(value = "Authorization") String authorizationHeader) {
        System.out.println("/token api invoked");
        try {
            String[] credentials = extractBasicCredentials(authorizationHeader);

            UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) userAuthenticationService.authenticateUser(credentials[0], credentials[1]);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = jwtService.generateToken(userDetails);

            TokenResponse tokenResponse = new TokenResponse(token, tokenExpiryInMillis);

            return ResponseEntity.ok().body(tokenResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            throw e;
        }
    }

    private String[] extractBasicCredentials(String authorizationHeader) {
        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            throw new IllegalArgumentException("Authorization header is required");
        }

        if (!authorizationHeader.startsWith("Basic ")) {
            throw new IllegalArgumentException("Authorization header must use Basic scheme");
        }

        String base64Credentials = authorizationHeader.substring(6).trim();
        if (base64Credentials.isEmpty()) {
            throw new IllegalArgumentException("Basic credentials are missing");
        }

        String decoded;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            decoded = new String(decodedBytes, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Base64 value in Authorization header", e);
        }

        int separator = decoded.indexOf(':');
        if (separator < 0) {
            throw new IllegalArgumentException("Basic credentials must be in username:password format");
        }

        String username = decoded.substring(0, separator).trim();
        String password = decoded.substring(separator + 1);
        if (username.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException("Username and password must not be blank");
        }
        return new String[]{username, password};
    }
}
