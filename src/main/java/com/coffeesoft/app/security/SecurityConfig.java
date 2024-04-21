package com.coffeesoft.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailManager() {

        UserDetails user = User.builder()
                .username("jhosep")
                .password("{noop}1234")
                .roles("EMPLOYEE").build();

        UserDetails user2 = User.builder()
                .username("kei")
                .password("{noop}1234")
                .roles("EMPLOYEE", "MANAGER").build();

        UserDetails user3 = User.builder()
                .username("koenji")
                .password("{noop}1234")
                .roles("EMPLOYEE", "MANAGER", "ADMIN").build();

        return new InMemoryUserDetailsManager(user, user2, user3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(config ->

            config
                    .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/form")
                                .loginProcessingUrl("/processLogin")
                                .defaultSuccessUrl("/home")
                                .permitAll()

                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}
