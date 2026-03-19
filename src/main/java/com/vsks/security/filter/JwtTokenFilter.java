package com.vsks.security.filter;

import com.vsks.security.principal.UserPrincipal;
import com.vsks.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Filtering the URI " + request.getRequestURI());
        this.request = request;
        this.response = response;
        this.filterChain = filterChain;
        if (!hasAuthorizationBearer(request)) {
            System.out.println("Request header contains no authorization token");
            filterChain.doFilter(request, response);
            return;
        }
        String token = getAccessToken(request);
        if (!jwtTokenUtil.validateAccessToken(token)) {
            System.out.println("Request header contains authorization token, but it is not validated");
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("Request header contains authorization token, and it is validated");
        setAuthenticationContext(token, request);
        System.out.println("Auth type: " + request.getAuthType() + ", DEV: " + request.isUserInRole("DEV") + ", ROLE_DEV: " + request.isUserInRole("ROLE_DEV") + ", User Principal: " + request.getUserPrincipal() + ", EMPTY: " + request.isUserInRole("") + ", NULL: " + request.isUserInRole(null));
        filterChain.doFilter(request, response);
    }

    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        System.out.println("Header=" + header);
        return (null != header) && header.startsWith("Bearer");
    }

    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split("\\s")[1].trim();
        return token;
    }

    private void setAuthenticationContext(String token, HttpServletRequest request) {
        UserPrincipal userPrincipal = getUserPrincipal(token);
        System.out.println("Authorities from token: " + userPrincipal.getAuthorities());
        if (null != userPrincipal.getUsername() && null == SecurityContextHolder.getContext().getAuthentication()) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private UserPrincipal getUserPrincipal(String token) {
        UserPrincipal userPrincipal = new UserPrincipal();
        String jwtSubject = jwtTokenUtil.getSubject(token);
        userPrincipal.setUsername(jwtSubject.split("\\|")[0]);
        String roles = jwtSubject.split("\\|")[1];
        userPrincipal.setAuthorities(Arrays.stream(roles.split(",")).map(SimpleGrantedAuthority::new).toList());
        return userPrincipal;
    }

}
