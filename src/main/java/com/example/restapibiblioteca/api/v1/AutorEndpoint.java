package com.example.restapibiblioteca.api.v1;

import com.example.restapibiblioteca.dto.AutorRequestCreate;
import com.example.restapibiblioteca.dto.AutorRequestUpdate;
import com.example.restapibiblioteca.dto.AutorView;
import com.example.restapibiblioteca.service.AutorService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/autor")
public class AutorEndpoint{

    private final AutorService service;

    public AutorEndpoint(AutorService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        return new ResponseEntity<>(new AutorView(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<?> find(@PathVariable String name) {
        return new ResponseEntity<>(new AutorView(service.findByName(name)), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<?> listAll() {
        List<AutorView> autorViewList = service.listAll().stream()
                .map(AutorView::new).collect(Collectors.toList());
        return new ResponseEntity<>(autorViewList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> list(Pageable pageable) {
        List<AutorView> autorViewList = service.list(pageable).stream()
                .map(AutorView::new).collect(Collectors.toList());
        PageImpl<AutorView> autorViewPage = new PageImpl<>(autorViewList, pageable, pageable.getPageSize());
        return new ResponseEntity<>(autorViewPage, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody AutorRequestCreate autorRequest) {
        return new ResponseEntity<>(new AutorView(service.save(autorRequest)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody AutorRequestUpdate autorRequest) {
        return new ResponseEntity<>(new AutorView(service.update(autorRequest)), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
