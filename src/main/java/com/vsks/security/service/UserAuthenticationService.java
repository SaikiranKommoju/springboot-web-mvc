package com.vsks.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserAuthenticationService {

    Authentication authenticateUser(String username, String password);

    void setUserInSession(UserDetails userDetails);

    UserDetails getUserFromSession();
}
