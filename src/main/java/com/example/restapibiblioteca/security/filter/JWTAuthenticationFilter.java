package com.example.restapibiblioteca.security.filter;

import com.example.restapibiblioteca.domain.security.ApplicationUser;
import com.example.restapibiblioteca.security.service.CustomUserDetailsService;
import com.example.restapibiblioteca.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ApplicationUser applicationUser = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    applicationUser.getUsername(), applicationUser.getPassword()
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        ApplicationUser principal = (ApplicationUser) authResult.getPrincipal();
        String token = jwtUtil.generateToken(principal);
        response.setHeader(HttpHeaders.AUTHORIZATION, token);
        response.getWriter().write("token: "+token+"expiration: "+jwtUtil.getExpiration(token.replace("Bearer ", "").trim()));
        chain.doFilter(request, response);
    }
}
