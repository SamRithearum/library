package com.sam.library.gateway.config;

import com.sam.library.gateway.repository.StudentRepository;
import com.sam.library.gateway.security.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableWebFluxSecurity
public class SecurityConfig {
    private StudentRepository studentRepository;

    @Autowired
    private JWTFilter filter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return studentId -> (UserDetails) studentRepository.getStudentByStudentId(studentId);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((authorize) -> {
                authorize.requestMatchers("/").permitAll();
                authorize.requestMatchers("/favicon.ico").permitAll();
                authorize.requestMatchers("/eureka/**").permitAll();
                authorize.requestMatchers("/swagger-ui.html").permitAll();
                authorize.requestMatchers("/swagger-ui/**").permitAll();
                authorize.requestMatchers("/v3/api-docs/**").permitAll();
                authorize.requestMatchers("/api/auth/**").permitAll();
                authorize.requestMatchers("/student/**").permitAll();
            })
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange((authorize) -> {
                    authorize.pathMatchers("/**").permitAll();
                    authorize.pathMatchers("/favicon.ico").permitAll();
                    authorize.pathMatchers("/eureka/**").permitAll();
                    authorize.pathMatchers("/swagger-ui.html").permitAll();
                    authorize.pathMatchers("/swagger-ui/**").permitAll();
                    authorize.pathMatchers("/v3/api-docs/**").permitAll();
                    authorize.pathMatchers("/api/auth/**").permitAll();
                    authorize.pathMatchers("/student/**").permitAll();
                    authorize.pathMatchers("/book/**").permitAll();
                });

        return http.build();
    }
}
