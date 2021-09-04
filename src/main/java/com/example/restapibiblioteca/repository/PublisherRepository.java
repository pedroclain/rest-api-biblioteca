package com.example.restapibiblioteca.repository;

import com.example.restapibiblioteca.domain.Gender;
import com.example.restapibiblioteca.domain.Publisher;
import com.example.restapibiblioteca.domain.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    @Query("select p from Publisher p where p.deleted = false")
    @Override
    List<Publisher> findAll();

    @Query("select p from Publisher p where p.deleted = false")
    @Override
    Page<Publisher> findAll(Pageable pageable);

    @Query("select p from Publisher p where p.deleted = false and p.id = ?1 ")
    @Override
    Optional<Publisher> findById(Long id);

    @Query("select p from Publisher p where p.name like %?1% and p.deleted = false")
    Page<Publisher> findByName(String name, Pageable pageable);

//    @Query("select p from Publisher p where p.deleted = true")
//    List<Publisher> findAllDisablePublisher();

    @Query("update Publisher p set p.deleted = true where p.id = ?1")
    @Modifying
    void delete(Long id);

    @Query("update Publisher p set p.name = ?2 where p.id = ?1")
    @Modifying
    void update(Long id, String name);

//    @Query("delete from Publisher p where p.deleted = true")
//    void deleteAllDisablePublisher();
}
