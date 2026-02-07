package com.Anudip.Smart.Skill.Workload.Management.System.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // ===== ADMIN ONLY (WRITE OPERATIONS) =====
                        .requestMatchers(HttpMethod.POST, "/api/skills/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/skills/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/user-skills/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/tasks/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/tasks/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/tasks/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/assignment/**").hasRole("ADMIN")

                        // ===== ADMIN + USER (READ ONLY) =====
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/skills/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/tasks/**").hasAnyRole("ADMIN", "USER")

                        // ===== EVERYTHING ELSE =====
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {});

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("user")
                .password("{noop}user123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
