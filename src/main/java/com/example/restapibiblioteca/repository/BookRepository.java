package com.example.restapibiblioteca.repository;

import com.example.restapibiblioteca.domain.Autor;
import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Gender;
import com.example.restapibiblioteca.domain.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b where b.deleted = false")
    @Override
    List<Book> findAll();

    @Query("select b from Book b where b.deleted = false")
    @Override
    Page<Book> findAll(Pageable pageable);

    @Query("select b from Book b where b.deleted = false and b.id=?1")
    @Override
    Optional<Book> findById(Long aLong);

    @Query("select b from Book b where b.name like %?1% and b.deleted = false")
    Page<Book> findByName(String name, Pageable pageable);

    @Query("select b from Book b where b.autor.name like %?1% and b.deleted = false")
    Page<Book> findByAutorName(String autor, Pageable pageable);

    @Query("select b from Book b where b.publisher.name like %?1% and b.deleted = false")
    Page<Book> findByPublisherName(String publisher, Pageable pageable);

    @Query("select b from Book b where b.gender.name like %?1% and b.deleted = false")
    Page<Book> findByGenderName(String gender, Pageable pageable);

    @Query("update Book b set b.deleted = true where b.id = ?1")
    @Modifying
    void delete(long id);

//    @Query("delete from Book b where b.deleted=true")
//    @Modifying
//    void deleteAllDisableBooks();

    @Query("update Book b set b.deleted = true where b.gender.id = ?1")
    @Modifying
    void deleteAllBooksRelatedToGender(Long id);

    @Query("update Book b set b.deleted = true where b.autor.id = ?1")
    @Modifying
    void deleteAllBooksRelatedToAutor(Long id);

    @Query("update Book b set b.deleted = true where b.publisher.id = ?1")
    @Modifying
    void deleteAllBooksRelatedToPublisher(Long id);

//    @Query("delete from Book b where b.gender.id = ?1 and b.deleted = true")
//    @Modifying
//    void deleteAllDisableBooksRelatedToGender(Long id);
//
//    @Query("delete from Book b where b.autor.id = ?1 and b.deleted = true")
//    @Modifying
//    void deleteAllDisableBooksRelatedToAutor(Long id);
//
//    @Query("delete from Book b where b.publisher.id = ?1 and b.deleted = true")
//    @Modifying
//    void deleteAllDisableBooksRelatedToPublisher(Long id);

    // TODO Update query
    @Query("update Book b set b.name = :name, b.gender = :gender, b.autor = :autor, b.publisher = :pub, b.publishDate = :pubDate where b.id = :id")
    @Modifying
    void update(@Param("id") Long id, @Param("name") String name, @Param("gender") Gender gender,
                @Param("autor") Autor autor, @Param("pub") Publisher pub, @Param("pubDate") LocalDate pubDate);

}
