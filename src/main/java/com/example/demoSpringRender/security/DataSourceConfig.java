package com.example.demoSpringRender.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver"); // Sử dụng driver của PostgreSQL
        dataSource.setUrl("jdbc:postgresql://dpg-ckigog212bvs738pfmvg-a.oregon-postgres.render.com:5432/renderproduct");
        dataSource.setUsername("renderproduct_user");
        dataSource.setPassword("1AB43lwOucjXHoAHeBgONXYZoZH5vtKW");
        return dataSource;
    }
}
