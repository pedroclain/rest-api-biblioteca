package com.example.restapibiblioteca.repository;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b where b.name like %?1%")
    Page<Book> findByName(String name, Pageable pageable);
    @Query("select b from Book b where b.autor.name like %?1%")
    Page<Book> findByAutorName(String autor, Pageable pageable);
    @Query("select b from Book b where b.publisher.name like %?1%")
    Page<Book> findByPublisherName(String publisher, Pageable pageable);
    @Query("select b from Book b where b.gender.name like %?1%")
    Page<Book> findByGenderName(String gender, Pageable pageable);
}
