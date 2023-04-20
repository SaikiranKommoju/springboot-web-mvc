package com.vsks.security.service;

import com.vsks.dao.UserDAO;
import com.vsks.dto.User;
import com.vsks.security.principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LocalUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        System.out.println("Loading user by username "  + emailId + " from User repo");
        Optional<User> user = userDAO.findUserByEmailId(emailId);
        user.orElseThrow(() -> new UsernameNotFoundException("User with e-Mail ID " + emailId + " is not found at us"));
        UserPrincipal userPrincipal = user.map(UserPrincipal::new).get();
        System.out.println("Granted authorities from DB: " + userPrincipal.getAuthorities());
        return userPrincipal;
    }
}
