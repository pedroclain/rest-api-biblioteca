package com.example.restapibiblioteca.api.v1;

import com.example.restapibiblioteca.dto.AutorRequestCreate;
import com.example.restapibiblioteca.dto.AutorRequestUpdate;
import com.example.restapibiblioteca.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/autor")
public class AutorEndpoint {

    private final AutorService service;

    public AutorEndpoint(AutorService service) {
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
    public ResponseEntity<?> save(@RequestBody AutorRequestCreate autorRequest) {
        return new ResponseEntity<>(service.save(autorRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody AutorRequestUpdate autorRequest) {
        return new ResponseEntity<>(service.update(autorRequest), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
