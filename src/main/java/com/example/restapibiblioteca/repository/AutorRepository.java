package com.example.restapibiblioteca.repository;

import com.example.restapibiblioteca.domain.Autor;
import com.example.restapibiblioteca.domain.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
