package com.example.restapibiblioteca.repository;

import com.example.restapibiblioteca.domain.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GenderRepository extends JpaRepository<Gender, Long> {

    @Query("select g from Gender g where g.deleted = false")
    @Override
    List<Gender> findAll();

    @Query("select g from Gender g where g.deleted = false")
    @Override
    Page<Gender> findAll(Pageable pageable);

    @Query("select g from Gender g where g.deleted = false and g.id = ?1 ")
    @Override
    Optional<Gender> findById(Long id);

    @Query("select g from Gender g where g.name like %?1% and g.deleted = false")
    Page<Gender> findByName(String name, Pageable pageable);

//    @Query("select g from Gender g where g.deleted = true")
//    List<Gender> findAllDisableGender();

    @Query("update Gender g set g.deleted = true where g.id = ?1")
    @Modifying
    void delete(Long id);

    @Query("update Gender g set g.name = ?2 where g.id = ?1")
    @Modifying
    void update(Long id, String name);

//    @Query("delete from Gender g where g.deleted = true")
//    void deleteAllDisableGender();
}
