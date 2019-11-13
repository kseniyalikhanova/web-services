package com.epam.travelagency.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties",
        properties = {"spring.liquibase.enabled=false", "spring.flyway.enabled=false"})
@EntityScan(basePackages = "com.epam.travelagency.entity")
@EnableJpaRepositories(basePackages = "com.epam.travelagency.repository")
@ComponentScan(basePackages = {"com.epam.travelagency.entity",
        "com.epam.travelagency.parser",
        "com.epam.travelagency.repository",
        "com.epam.travelagency.config",
        "com.epam.travelagency.util"})
public class RepositoryTestConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
