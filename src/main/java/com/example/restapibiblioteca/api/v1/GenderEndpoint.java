package com.example.restapibiblioteca.api.v1;

import com.example.restapibiblioteca.domain.Gender;
import com.example.restapibiblioteca.dto.GenderRequestCreate;
import com.example.restapibiblioteca.dto.GenderRequestUpdate;
import com.example.restapibiblioteca.dto.GenderView;
import com.example.restapibiblioteca.repository.GenderRepository;
import com.example.restapibiblioteca.service.GenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/gender")
public class GenderEndpoint{

    private final GenderService service;

    public GenderEndpoint(GenderService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<?> find(@PathVariable String name) {
        return new ResponseEntity<>(service.findByName(name), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody GenderRequestCreate genderRequest) {
        return new ResponseEntity<>(service.save(genderRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody GenderRequestUpdate genderRequest) {
        return new ResponseEntity<>(service.update(genderRequest), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
