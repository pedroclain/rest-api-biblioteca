//package com.example.restapibiblioteca.service;
//
//import com.example.restapibiblioteca.domain.Autor;
//import com.example.restapibiblioteca.domain.Gender;
//import com.example.restapibiblioteca.dto.GenderRequestCreate;
//import com.example.restapibiblioteca.dto.GenderRequestUpdate;
//import com.example.restapibiblioteca.exception.ResourceNotFound;
//import com.example.restapibiblioteca.repository.AutorRepository;
//import com.example.restapibiblioteca.repository.GenderRepository;
//import com.example.restapibiblioteca.util.gender.GenderCreator;
//import com.example.restapibiblioteca.util.gender.GenderRequestCreateCreator;
//import com.example.restapibiblioteca.util.gender.GenderRequestUpdateCreator;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.BDDMockito;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//public class JpaTest {
//    @Autowired
//    private AutorRepository repository;
//
//    @Test
//    void save() {
//        repository.save(new Autor("test"));
//        assertEquals("", "");
//    }
//}