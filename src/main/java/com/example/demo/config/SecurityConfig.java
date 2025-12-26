// package com.example.demo.config;

// import com.example.demo.security.CustomUserDetailsService;
// import com.example.demo.security.JwtAuthenticationFilter;
// import com.example.demo.security.JwtUtil;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtUtil jwtUtil;
//     private final CustomUserDetailsService customUserDetailsService;
//     private final JwtAuthenticationFilter jwtAuthenticationFilter;

//     public SecurityConfig(JwtUtil jwtUtil,
//                           CustomUserDetailsService customUserDetailsService,
//                           JwtAuthenticationFilter jwtAuthenticationFilter) {
//         this.jwtUtil = jwtUtil;
//         this.customUserDetailsService = customUserDetailsService;
//         this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//     }

//     // Password encoder for hashing passwords
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     // Authentication provider using CustomUserDetailsService and password encoder
//     @Bean
//     public DaoAuthenticationProvider authenticationProvider() {
//         DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//         authProvider.setUserDetailsService(customUserDetailsService); // FIXED
//         authProvider.setPasswordEncoder(passwordEncoder());
//         return authProvider;
//     }

//     // Expose AuthenticationManager as a bean
//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }

//     // Security filter chain configuration
//     @Bean
//     public SecurityFilterChain filterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//     .anyRequest().permitAll() // allow all requests while testing in sandbox
// )
//             .authenticationProvider(authenticationProvider())
//             .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }
// }



package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Allows H2 and Sandbox frames
            .authorizeHttpRequests(auth -> auth
                // 1. Allow the root and simple-status for sandbox preview & health checks
                .requestMatchers("/", "/simple-status", "/auth/**", "/h2-console/**").permitAll()
                
                // 2. Specific authority requirements for Events
                .requestMatchers("/api/events/**").hasAnyAuthority("ADMIN", "PUBLISHER")
                
                // 3. All other requests (Subscriptions, Updates, etc.) require a valid JWT
                .anyRequest().authenticated()
            )
            // 4. Ensure the session is STATELESS (critical for JWT)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // 5. Add our JWT filter before the standard UsernamePassword filter
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}