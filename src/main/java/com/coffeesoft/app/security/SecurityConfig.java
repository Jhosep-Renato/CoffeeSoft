package com.coffeesoft.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.GrantedAuthority;
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
                .roles("MANAGER").build();

        UserDetails user3 = User.builder()
                .username("koenji")
                .password("{noop}1234")
                .roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user, user2, user3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(config ->

            config
                    .requestMatchers(HttpMethod.GET, "/home-cashier", "/show-sale").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.POST, "/processProduct").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.GET, "/home-admin").hasRole("ADMIN")
                    .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/form")
                                .loginProcessingUrl("/processLogin")
                                .successHandler((request, response, authentication) -> {

                                    for (GrantedAuthority auth: authentication.getAuthorities()) {

                                        if (auth.getAuthority().equals("ROLE_EMPLOYEE")) {

                                            response.sendRedirect("/home-cashier");
                                        } else if (auth.getAuthority().equals("ROLE_ADMIN")) {

                                            response.sendRedirect("/home-admin");
                                        }
                                    }
                                })
                                .permitAll()

                )
                .logout(logout -> {
                    logout.logoutSuccessUrl("/for");
                    logout.permitAll();
                })
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
