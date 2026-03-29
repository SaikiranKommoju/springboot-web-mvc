package com.vsks.security.config;

import com.vsks.security.filter.JwtTokenValidateFilter;
import com.vsks.security.filter.LocalAuthenticationFilter;
import com.vsks.security.handler.LocalAuthenticationFailureHandler;
import com.vsks.security.handler.LocalAuthenticationSuccessHandler;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer {

    private static final String[] PERMITTED_PATHS = {"/loginPage", "/login", "/logout", "/token", "/wish", "/css/**", "/js/**", "/images/**"};

    static class NoPasswordEncoder implements PasswordEncoder {
        @Override
        public String encode(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override
        public boolean matches(CharSequence charSequence, String s) {
            return charSequence.toString().equals(s);
        }
    }

    @Autowired
    private JwtTokenValidateFilter jwtTokenValidateFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("Creating Password Encoder");
        //return new BCryptPasswordEncoder();
        return new NoPasswordEncoder();
    }

    @Bean
    @SuppressWarnings("deprecation")
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        System.out.println("Creating Authentication Manager");
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   AuthenticationManager authenticationManager,
                                                   LocalAuthenticationSuccessHandler localAuthenticationSuccessHandler,
                                                   LocalAuthenticationFailureHandler localAuthenticationFailureHandler) throws Exception {
        System.out.println("Configuring security over HTTP requests");

        LocalAuthenticationFilter localAuthenticationFilter = new LocalAuthenticationFilter();
        localAuthenticationFilter.setAuthenticationManager(authenticationManager);
        localAuthenticationFilter.setAuthenticationSuccessHandler(localAuthenticationSuccessHandler);
        localAuthenticationFilter.setAuthenticationFailureHandler(localAuthenticationFailureHandler);
        localAuthenticationFilter.setFilterProcessesUrl("/login");

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)) // Allow sessions for login form, but use JWT for API
                .authorizeHttpRequests(auth -> auth
                        // Permit forwarded JSP rendering and error dispatches to avoid login page redirect loops.
                        // Spring Security 6 enables authorization for internal redirects (like JSP, CSS, JS files etc.) also
                        // Hence allows them go through Security Filter chain
                        // Below call will stop them
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .requestMatchers(PERMITTED_PATHS).permitAll()
                        .requestMatchers("/employee/*").hasAnyAuthority("READ", "WRITE", "DELETE")
                        .requestMatchers("/employee").hasAnyAuthority("WRITE", "DELETE")
                        .anyRequest().authenticated()
                ).formLogin(form -> form
                                .loginPage("/loginPage")
                                .loginProcessingUrl("/login")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/loginPage")
                        .invalidateHttpSession(true)
                )
                .addFilterAt(localAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtTokenValidateFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
