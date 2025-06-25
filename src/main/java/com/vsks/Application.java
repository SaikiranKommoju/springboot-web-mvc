package com.vsks;

import com.vsks.config.DBConfigurer;
import com.vsks.security.config.WebSecurityConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Calendar;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.vsks.repo")
@Import({/*DBConfigurer.class, */WebSecurityConfigurer.class})
public class Application {

    @Bean
    @Scope
    public Calendar getCalendar() {
        return Calendar.getInstance();
    }

    @Bean
    public ViewResolver getViewResolver() {
        System.out.println("AppConfig.getViewResolver()");
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setPrefix("/WEB-INF/jsp/");
        vr.setSuffix(".jsp");
        return vr;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    //@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/student/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedOrigins("http://localhost:3000", "http://localhost:3030");
            }
        };
    }

    public static void main(String[] args) {
        System.out.println("Running Spring Boot app ...");
        //System.setProperty("username", "saikiranprodsyspro");
        SpringApplication.run(Application.class);
    }

}