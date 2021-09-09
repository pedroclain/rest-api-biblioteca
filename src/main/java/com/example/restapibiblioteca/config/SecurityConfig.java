package com.example.restapibiblioteca.config;

import com.example.restapibiblioteca.security.filter.JWTAuthenticationFilter;
import com.example.restapibiblioteca.security.filter.JWTAuthorizationFilter;
import com.example.restapibiblioteca.security.service.CustomUserDetailsService;
import com.example.restapibiblioteca.util.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }

    private final CustomUserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JWTUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("pedro").password("noop{123}").roles("ADMIN");
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/rest-api-docs/**").permitAll()
                .antMatchers(GET, "/api/v*/*/").permitAll()
                .antMatchers(GET, "/api/v*/*/find/name").permitAll()
                .antMatchers(GET, "/api/v*/*/all").hasAuthority("READ_ADMIN")
                .antMatchers(POST, "/api/v*/*").hasAuthority("WRITE")
                .antMatchers(PUT, "/api/v*/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers(DELETE, "/api/v*/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(jwtUtil, authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
