package com.vsks;

import com.vsks.config.DBConfigurer;
import com.vsks.security.config.WebSecurityConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Calendar;

@SpringBootApplication
//@Import({DBConfigurer.class, WebSecurityConfigurer.class})
public class Application {

    @Bean
    public Calendar getCalendar() {
        return Calendar.getInstance();
    }

    @Bean
    public ViewResolver getViewResolver() {
        System.out.println("AppConfig.getViewResolver()");
        ViewResolver vr = new InternalResourceViewResolver();
        ((InternalResourceViewResolver) vr).setPrefix("/WEB-INF/pages/");
        ((InternalResourceViewResolver) vr).setSuffix(".jsp");
        return vr;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}