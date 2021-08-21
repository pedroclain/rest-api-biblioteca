package com.example.restapibiblioteca.repository;

import com.example.restapibiblioteca.domain.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenderRepository extends JpaRepository<Gender, Long> {
    Optional<Gender> findByName(String name);
}
