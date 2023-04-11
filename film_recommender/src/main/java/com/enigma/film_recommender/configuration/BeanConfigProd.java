package com.enigma.film_recommender.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
@PropertySource("classpath:application.properties")
public class BeanConfigProd {
    @Value("${dbDriver}")
    private String dbDriver;
    @Value("${dbUrl}")
    private String dbUrl;
    @Value("${dbUser}")
    private String dbUser;
    @Value("${dbPassword}")
    private String dbPassword;

    @Bean
    DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(dbUrl);
        driverManagerDataSource.setUsername(dbUser);
        driverManagerDataSource.setPassword(dbPassword);
        driverManagerDataSource.setDriverClassName(dbDriver);

        return driverManagerDataSource;
    }
}
