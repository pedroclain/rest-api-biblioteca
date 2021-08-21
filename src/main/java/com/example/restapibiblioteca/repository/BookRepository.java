package com.example.restapibiblioteca.repository;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
