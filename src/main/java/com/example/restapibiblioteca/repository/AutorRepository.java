package com.example.restapibiblioteca.repository;

import com.example.restapibiblioteca.domain.Autor;
import com.example.restapibiblioteca.domain.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("select a from Autor a where a.deleted = false")
    @Override
    List<Autor> findAll();

    @Query("select a from Autor a where a.deleted = false")
    @Override
    Page<Autor> findAll(Pageable pageable);

    @Query("select a from Autor a where a.deleted = false and a.id = ?1 ")
    @Override
    Optional<Autor> findById(Long id);

    @Query("select a from Autor a where a.name like %?1% and a.deleted = false")
    Page<Autor> findByName(String name, Pageable pageable);

//    @Query("select a from Autor a where a.deleted = true")
//    List<Autor> findAllDisableAutor();

    @Query("update Autor a set a.deleted = true where a.id = ?1")
    @Modifying
    void delete(Long id);

    @Query("update Autor a set a.name = ?2 where a.id = ?1")
    @Modifying
    void update(Long id, String name);

//    @Query("delete from Autor a where a.deleted = true")
//    void deleteAllDisableAutor();
}
