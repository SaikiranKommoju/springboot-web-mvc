package com.vsks.security.config;

import com.vsks.dao.UserDAO;
import com.vsks.security.filter.JwtTokenFilter;
import com.vsks.security.service.LocalUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

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
    private LocalUserDetailsService localUserDetailsService;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("Configuring Auth Manager Builder");
        /*auth.inMemoryAuthentication()
                .withUser("saikiran_kommoju@vsks.com")
                .password("saikiran123")
                .authorities("DEV", "UI");*/
        auth.userDetailsService(localUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("Creating Password Encoder");
        //return new BCryptPasswordEncoder();
        return new NoPasswordEncoder();
    }

    //To avoid the in-compatibility between Spring Security & Spring Boot versions
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        System.out.println("Creating Authentication Manager");
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        System.out.println("Configuring security over HTTP requests");
        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//To tell to Spring Security to not create sessions to make it Stateful which is not in a concept of JWT

        httpSecurity.authorizeRequests()
                /*.antMatchers("/user/api/v1/consumeServiceA").hasAuthority("DEV")
                .antMatchers("/user/api/v1/consumeServiceE").hasAuthority("DB")*/
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();

        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);//To tell to Spring Security to call this filter before UsernamePasswordAuthenticationFilter
    }

}
