package com.example.restapibiblioteca.repository;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);
    Optional<List<Book>> findByAutorName(String autor);
    Optional<List<Book>> findByPublisherName(String publisher);
}
