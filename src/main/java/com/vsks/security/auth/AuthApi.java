package com.vsks.security.auth;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.vsks.security.principal.UserPrincipal;
import com.vsks.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthApi {

    @Autowired
    @Lazy
    AuthenticationManager authManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/auth/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        System.out.println("Authenticating the user with e-Mail ID " + authRequest.getEmailId() + " & password " + authRequest.getPassword());
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmailId(), authRequest.getPassword()));
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(userPrincipal);
            System.out.println("Access Token: " + accessToken);
            AuthResponse authResponse = new AuthResponse(userPrincipal.getUsername(), accessToken);
            return ResponseEntity.ok().body(authResponse);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
