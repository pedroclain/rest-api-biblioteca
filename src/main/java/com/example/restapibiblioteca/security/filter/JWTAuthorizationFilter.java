package com.example.restapibiblioteca.security.filter;

import com.example.restapibiblioteca.domain.security.ApplicationUser;
import com.example.restapibiblioteca.security.service.CustomUserDetailsService;
import com.example.restapibiblioteca.util.JWTUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace("Bearer ", "").trim();
        ApplicationUser applicationUser = (ApplicationUser) userDetailsService.loadUserByUsername(jwtUtil.getUsername(token));
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                applicationUser.getUsername(), null, applicationUser.getAuthorities()
        ));
        chain.doFilter(request, response);
    }
}
