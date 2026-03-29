package com.codility.configuration;

import com.codility.beans.LegacyPaymentsService;
import com.codility.beans.UsersRepository;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource({"classpath:production.properties"})
@ComponentScan(basePackages = "com.codility.utils")
@Import(MaintenanceConfig.class)
public class SystemConfig {

    @Bean(initMethod = "initialize")
    public UsersRepository usersRepository() {
        return new UsersRepository();
    }

    @Bean(name = "paymentsService")
    public LegacyPaymentsService legacyPaymentsService() {
        return new LegacyPaymentsService();
    }
}
