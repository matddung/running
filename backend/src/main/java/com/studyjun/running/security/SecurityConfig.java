package com.studyjun.running.security;

import com.studyjun.running.security.handler.CustomAuthenticationEntryPoint;
import com.studyjun.running.security.service.CustomDefaultOAuth2UserService;
import com.studyjun.running.repository.CustomAuthorizationRequestRepository;
import com.studyjun.running.security.handler.CustomSimpleUrlAuthenticationFailureHandler;
import com.studyjun.running.security.handler.CustomSimpleUrlAuthenticationSuccessHandler;
import com.studyjun.running.security.service.CustomOncePerRequestFilter;
import com.studyjun.running.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomDefaultOAuth2UserService customOAuth2UserService;
    private final CustomSimpleUrlAuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final CustomSimpleUrlAuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final CustomAuthorizationRequestRepository customAuthorizationRequestRepository;

    private static final String[] ALLOWED_URIS = {
            "/", "/error", "/favicon.ico", "/*.html", "/*.css", "/*.js",
            "/swagger", "/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs/**", "/v3/api-docs/**",
            "/api/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                .csrf().disable()
                .formLogin().disable()
                .headers(headers -> headers.frameOptions().sameOrigin())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(h -> h
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
                .authorizeHttpRequests(request -> request
                        .requestMatchers(ALLOWED_URIS).permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(o -> o
                        .authorizationEndpoint(a -> a
                                .baseUri("/oauth2/authorize")
                                .authorizationRequestRepository(customAuthorizationRequestRepository))
                        .redirectionEndpoint(r -> r
                                .baseUri("/login/oauth2/code/*"))
                        .userInfoEndpoint(u -> u
                                .userService(customOAuth2UserService))
                        .successHandler(oAuth2AuthenticationSuccessHandler)
                        .failureHandler(oAuth2AuthenticationFailureHandler))
                .addFilterBefore(customOncePerRequestFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public CustomOncePerRequestFilter customOncePerRequestFilter() {
        return new CustomOncePerRequestFilter();
    }
}
