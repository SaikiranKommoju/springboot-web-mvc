package com.vsks.security.service;

import com.vsks.dao.UserDAO;
import com.vsks.dto.User;
import com.vsks.security.principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LocalUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        System.out.println("Loading user by username "  + emailId + " from User repo");
        UserPrincipal userPrincipal = userDAO.findUserByEmailId(emailId)
                .map(UserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with e-Mail ID " + emailId + " is not found at us"));
        System.out.println("User Principal: " + userPrincipal);
        //userPrincipal.setPassword(encodePassword(userPrincipal.getPassword()));
        return userPrincipal;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
