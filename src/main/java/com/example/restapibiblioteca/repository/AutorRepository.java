package com.example.restapibiblioteca.repository;

import com.example.restapibiblioteca.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("select a from Autor a where a.name like %?1%")
    Optional<Autor> findByName(String name);
}
