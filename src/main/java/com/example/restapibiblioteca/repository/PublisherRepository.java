package com.example.restapibiblioteca.repository;

import com.example.restapibiblioteca.domain.Gender;
import com.example.restapibiblioteca.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
