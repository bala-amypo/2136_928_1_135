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
            // 1. Disable CSRF as we are using JWT (Stateless)
            .csrf(csrf -> csrf.disable())
            
            // 2. Allow frames (Required for H2 Console and some Sandbox Previewers)
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            
            // 3. Request Authorization Rules
            .authorizeHttpRequests(auth -> auth
                // Publicly accessible paths
                .requestMatchers(
                    "/", 
                    "/error",
                    "/auth/**", 
                    "/simple-status", 
                    "/h2-console/**",
                    "/v3/api-docs/**", 
                    "/swagger-ui/**", 
                    "/swagger-ui.html"
                ).permitAll()
                
                // Role-based access for Events
                .requestMatchers("/api/events/**").hasAnyAuthority("ADMIN", "PUBLISHER")
                
                // All other requests require a valid JWT token
                .anyRequest().authenticated()
            )
            
            // 4. Set Session Management to Stateless
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            // 5. Add the JWT Filter before the standard Username/Password Filter
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