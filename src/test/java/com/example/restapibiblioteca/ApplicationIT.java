package com.example.restapibiblioteca;

import com.example.restapibiblioteca.api.v1.GenderEndpoint;
import com.example.restapibiblioteca.domain.Autor;
import com.example.restapibiblioteca.domain.Gender;
import com.example.restapibiblioteca.util.autor.AutorCreator;
import com.example.restapibiblioteca.util.gender.GenderCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ApplicationIT {

    @Autowired
    private GenderEndpoint genderEndpoint;
    @LocalServerPort
    private int port;
    private final String BASE_URL = "http://localhost:"+port+"/api/v1/";

    @Test
    void findByName_ReturnAutorAsAutorView_WhenSuccessful() {
        Autor autor = AutorCreator.createdAutor();
        Gender expectedGender = GenderCreator.createdGender();
        ResponseEntity<Gender> resourceGender = new RestTemplate().exchange(BASE_URL + "/gender/find/id/1", HttpMethod.GET, null, Gender.class);
    }
}
