package com.coffeesoft.app.security;

import com.coffeesoft.app.service.sauthentication.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserService userService) {

        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());

        return auth;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(config ->

            config
                    .requestMatchers(HttpMethod.GET, "/home-cashier", "/show-sale").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.POST, "/processProduct").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.GET, "/home-admin").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/process-request").hasRole("ADMIN")
                    .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/form")
                                .loginProcessingUrl("/processLogin")
                                .successHandler((request, response, authentication) -> {

                                    for (GrantedAuthority auth: authentication.getAuthorities()) {

                                        if (auth.getAuthority().equals("ROLE_EMPLOYEE")) {

                                            response.sendRedirect("/coffee-soft/home-cashier");
                                        } else if (auth.getAuthority().equals("ROLE_ADMIN")) {

                                            response.sendRedirect("/coffee-soft/home-admin");
                                        }
                                    }
                                })
                                .permitAll()

                )
                .logout(logout -> {
                    logout.logoutSuccessUrl("/form");
                    logout.permitAll();
                })
                .exceptionHandling(forbidden -> forbidden.accessDeniedPage("/forbidden"))
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
