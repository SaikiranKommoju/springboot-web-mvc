package com.vsks.security.handler;

import com.vsks.constants.StringConstants;
import com.vsks.security.service.JwtService;
import com.vsks.security.service.UserAuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.vsks.constants.StringConstants.JWT_TOKEN_COOKIE_NAME;

@Component
public class LocalAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        System.out.println("LocalAuthenticationSuccessHandler.onAuthenticationSuccess - Handler invoked!");

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("User authenticated: " + userDetails.getUsername());

        UserDetails userInSession = userAuthenticationService.getUserFromSession();
        if (null == userInSession || !userDetails.getUsername().equals(userInSession.getUsername())) {
            userAuthenticationService.setUserInSession(userDetails);
            System.out.println("User is set in session");
        } else {
            System.out.println("User is already in session");
        }

        String token = jwtService.generateToken(userDetails);
        addTokenAsCookie(response, token);
        System.out.println("Add token as cookie");

        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        String targetUrl = null != savedRequest ? savedRequest.getRedirectUrl() : "/";
        System.out.println("Redirecting to: " + targetUrl);

        response.sendRedirect(targetUrl);
    }

    private void addTokenAsCookie(HttpServletResponse response, String token) {
        Cookie jwtCookie = new Cookie(JWT_TOKEN_COOKIE_NAME, token);
        jwtCookie.setHttpOnly(false);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(3600);
        response.addCookie(jwtCookie);
    }
}
