package com.example.restapibiblioteca.security.service;

import com.example.restapibiblioteca.repository.ApplicationUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ApplicationUserRepository repository;

    public CustomUserDetailsService(ApplicationUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
