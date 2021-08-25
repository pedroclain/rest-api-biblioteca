package com.example.restapibiblioteca.repository;

import com.example.restapibiblioteca.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByName(String name);


    Optional<Autor> findByName(String name);
}
