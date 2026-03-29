package com.vsks.security.service;

import com.vsks.repo.UserRepository;
import com.vsks.security.principal.LocalUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LocalUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        LocalUserDetails userPrincipal = userRepository.findUserByEmailId(emailId)
                .map(LocalUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email Id " + emailId));
        System.out.println("User Principal: " + userPrincipal);
        //userPrincipal.setPassword(encodePassword(userPrincipal.getPassword()));
        return userPrincipal;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
