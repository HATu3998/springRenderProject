package com.example.demoSpringRender.security;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.authorizeHttpRequests(configurer ->
        configurer
            .requestMatchers("/", "/product/**","/temp").permitAll()
            .requestMatchers("/leaders","proUpdate","update").hasRole("ADMIN")
            .anyRequest().authenticated()
    )

            .formLogin(formLogin ->
                formLogin
                    .loginPage("/showLoginPage")
                    .loginProcessingUrl("/authenticateTheUser")
                    .defaultSuccessUrl("/", true)
                    .permitAll()
            )
            .logout(logout -> logout.permitAll())
            .exceptionHandling(configurer -> configurer.accessDeniedPage("/errorLogin"));

        return http.build();
    }
}
