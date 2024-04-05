package com.qb.ecommerce.config;

import com.qb.ecommerce.util.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.qb.ecommerce.model.enums.Permission.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/auth/**",
                        "/product",
                        "/product/top-rated-products",
                        "/product/best-seller-products",
                        "/product/by-filter",
                        "/product/{productId}",
                        "/category",
                        "/category/top-ordered-categories",
                        "/category/{categoryId}"
                )
                .permitAll()

                .requestMatchers(POST, "/product/crete").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                .requestMatchers(PUT, "/product/update").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                .requestMatchers(DELETE, "/product/{productId}").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())

                .requestMatchers(POST, "/category/crete").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                .requestMatchers(PUT, "/category/update").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                .requestMatchers(DELETE, "/category/{categoryId}").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())


                .requestMatchers(GET, "/order").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())

                .requestMatchers(POST, "/order/create").authenticated()
                .requestMatchers(PUT, "/order/update").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                .requestMatchers(DELETE, "/order/{orderId}").hasAuthority(ADMIN_DELETE.name())

                .requestMatchers(GET, "/user").hasAuthority(ADMIN_READ.name())
                .requestMatchers(POST, "/user/create").hasAuthority(ADMIN_CREATE.name())
                .requestMatchers(PUT, "/user/update").hasAuthority(ADMIN_UPDATE.name())
                .requestMatchers(DELETE, "/user/{userId}").hasAuthority(ADMIN_DELETE.name())

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;

        return http.build();
    }
}
