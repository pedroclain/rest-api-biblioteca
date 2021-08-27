package com.example.restapibiblioteca.api.v1;

import com.example.restapibiblioteca.dto.GenderRequestCreate;
import com.example.restapibiblioteca.dto.GenderRequestUpdate;
import com.example.restapibiblioteca.dto.GenderView;
import com.example.restapibiblioteca.service.GenderService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/gender")
public class GenderEndpoint{

    private final GenderService service;

    public GenderEndpoint(GenderService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        return new ResponseEntity<>(new GenderView(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<?> find(@PathVariable String name) {
        return new ResponseEntity<>(new GenderView(service.findByName(name)), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<?> listAll() {
        List<GenderView> genderViewList = service.listAll().stream()
                .map(GenderView::new).collect(Collectors.toList());
        return new ResponseEntity<>(genderViewList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> list(Pageable pageable) {
        List<GenderView> genderViewList = service.list(pageable).stream()
                .map(GenderView::new).collect(Collectors.toList());
        PageImpl<GenderView> genderViewPage = new PageImpl<>(genderViewList, pageable, pageable.getPageSize());
        return new ResponseEntity<>(genderViewPage, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid GenderRequestCreate genderRequest) {
        return new ResponseEntity<>(new GenderView(service.save(genderRequest)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid GenderRequestUpdate genderRequest) {
        return new ResponseEntity<>(new GenderView(service.update(genderRequest)), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
