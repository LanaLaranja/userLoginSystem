// Alana Cristina Muller S1569092
/* Security Configuration - This class sets up Spring Security for the application, defining authentication and authorization rules */
package com.cloudtechsolutions.wiki.security;

import com.cloudtechsolutions.wiki.model.Admin;
import com.cloudtechsolutions.wiki.repository.AdminRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// Spring Security configuration class
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //Bean is a Spring annotation that indicates that a method produces a bean to be managed by the Spring container. It is used to define and configure beans within a configuration class, allowing for dependency injection and management of application components.
    //SecurityFilterChain is a Spring Security interface that represents a chain of security filters that process incoming HTTP requests. It is used to define the security configuration for web applications, specifying how requests should be authenticated and authorized.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Rely on Spring Security auto-configuration which will create a
        // DaoAuthenticationProvider from the UserDetailsService and
        // PasswordEncoder beans defined below.

        // HTTP security configuration: protect all endpoints by default,
        // allow anonymous access to static resources and signup endpoints,
        // configure the login page and redirect URLs.
        http

                
                .authorizeHttpRequests(auth -> auth
                // allow unauthenticated access to common static resource folders
                .requestMatchers(
                    "/",
                    "/error",
                    "/articles",
                    "/articles/**",
                    "/article",
                    "/article/**",
                    "/categories",
                    "/categories/**",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/webjars/**",
                    "/favicon.ico",
                    "/signup",
                    "/register").permitAll()
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/welcome", true)
                // on failure, return to the login page with an error flag so we can render a message
                .failureUrl("/login?error=true")
                .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(AdminRepository userRepository) {
        return username -> {
            // Lookup by email first (primary login identifier), then by name
            // to allow users to login with either value.
            Admin user = userRepository.findByEmail(username);
            if (user == null) {
                user = userRepository.findByName(username);
            }
            if (user == null) {
                throw new UsernameNotFoundException("User not found: " + username);
            }
            // Build a Spring Security UserDetails object. We use the user's
            // email as the canonical username in the security context.
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles("ADMIN")
                    .build();
        };
    }
    // No explicit AuthenticationProvider bean: Spring Boot will auto-configure
    // a DaoAuthenticationProvider using the UserDetailsService and
    // BCryptPasswordEncoder beans declared above.

}
