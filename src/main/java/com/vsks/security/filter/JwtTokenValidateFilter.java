package com.vsks.security.filter;

import static com.vsks.constants.StringConstants.JWT_TOKEN_COOKIE_NAME;

import com.vsks.security.principal.LocalUserDetails;
import com.vsks.security.service.JwtService;
import jakarta.servlet.http.Cookie;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Component
public class JwtTokenValidateFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    private static final List<String> PATHS_TO_SKIP = List.of("/wish", "/loginPage", "/WEB-INF/jsp", "/login", "/logout", "/token", "/css", "/js", "/images");

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return PATHS_TO_SKIP.stream()
                .anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JWT token validate filter execution starts");
        System.out.println("Filtered the URI: " + request.getRequestURI());

        String token = getToken(request);

        if (StringUtils.isBlank(token) || !jwtService.validateToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("Request contains valid authorization token");

        setUserInSession(request, token);

        filterChain.doFilter(request, response);

        System.out.println("JWT token validate filter execution ends");
    }

    private String getTokenFromCookie(HttpServletRequest request) {
        return null != request.getCookies() ?
                Arrays.stream(request.getCookies())
                        .filter(cookie -> JWT_TOKEN_COOKIE_NAME.equals(cookie.getName()))
                        .map(Cookie::getValue)
                        .findFirst()
                        .orElse(null)
                : null;
    }

    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return null != header && header.startsWith("Bearer");
    }

    private String getToken(HttpServletRequest request) {
        return hasAuthorizationBearer(request) ? getTokenFromHeader(request) : getTokenFromCookie(request);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header.split("\\s")[1].trim();
    }

    private void setUserInSession(HttpServletRequest request, String token) {
        UserDetails userDetails = getUserDetails(token);
        if (null != userDetails.getUsername() && null == SecurityContextHolder.getContext().getAuthentication()) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("User is set in session");
        }
    }

    private LocalUserDetails getUserDetails(String token) {
        LocalUserDetails userDetails = new LocalUserDetails();
        String jwtSubject = jwtService.getSubject(token);
        String username = jwtSubject.split("\\|")[0];
        String authorities = jwtSubject.split("\\|")[1];
        userDetails.setUsername(username);
        userDetails.setAuthorities(Arrays.stream(authorities.split(",")).map(SimpleGrantedAuthority::new).toList());
        return userDetails;
    }
}
