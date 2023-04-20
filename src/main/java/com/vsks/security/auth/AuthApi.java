package com.vsks.security.auth;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.vsks.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    AuthenticationManager authManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        System.out.println("Authenticating the user with e-Mail ID " + authRequest.getEmailId() + " & password " + authRequest.getPassword());
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmailId(), authRequest.getPassword()));
            /*UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(userPrincipal);*/
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            System.out.println("Access Token: " + accessToken);
            AuthResponse authResponse = new AuthResponse(user.getUsername(), accessToken);
            return ResponseEntity.ok().body(authResponse);
        } catch (BadCredentialsException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
