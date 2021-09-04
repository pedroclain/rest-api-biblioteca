package com.example.restapibiblioteca.api.v1;

import com.example.restapibiblioteca.domain.Autor;
import com.example.restapibiblioteca.dto.view.AutorView;
import com.example.restapibiblioteca.dto.request.AutorRequestCreate;
import com.example.restapibiblioteca.dto.request.AutorRequestUpdate;
import com.example.restapibiblioteca.exception.ResourceNotFound;
import com.example.restapibiblioteca.exception.handler.ExceptionDetails;
import com.example.restapibiblioteca.service.AutorService;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Endpoint to manage Author")
@RestController
@RequestMapping("api/v1/autor")
public class AutorEndpoint{

    private final AutorService service;

    public AutorEndpoint(AutorService service) {
        this.service = service;
    }

    @ApiOperation(value = "Return a list of all Authors", response = AutorView.class)
    @GetMapping("all")
    public ResponseEntity<?> listAll() {
        List<AutorView> autorViewList = service.listAll().stream()
                .map(AutorView::new).collect(Collectors.toList());
        return new ResponseEntity<>(autorViewList, HttpStatus.OK);
    }

    @ApiOperation(value = "Return all Authors inside a pages", response = AutorView.class)
    @GetMapping
    public ResponseEntity<?> list(Pageable pageable) {
        return new ResponseEntity<>(autorPageToAutorViewPage(service.list(pageable)), HttpStatus.OK);
    }

    @ApiOperation(value = "Return a Author by id number", response = AutorView.class)
    @GetMapping("find/id/{id}")
    public ResponseEntity<?> findById(@ApiParam("Author id number to find") @PathVariable Long id) {
        return new ResponseEntity<>(new AutorView(service.findById(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "Return Authors by name inside a page", response = AutorView.class)
    @GetMapping("find/name/{name}")
    public ResponseEntity<?> findByName(@ApiParam("A part of author name to find") @PathVariable String name, Pageable pageable) {
        return new ResponseEntity<>(autorPageToAutorViewPage(service.findByName(name, pageable)),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new Author", response = AutorView.class, code = 201)
    @PostMapping
    public ResponseEntity<?> save(@ApiParam("Json request author body to save") @RequestBody @Valid AutorRequestCreate autorRequest) {
        return new ResponseEntity<>(new AutorView(service.save(autorRequest)), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a Author by id number", code = 204)
    @PutMapping
    public ResponseEntity<?> update(@ApiParam("Json request author body to update") @RequestBody @Valid AutorRequestUpdate autorRequest) {
        service.update(autorRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Soft delete a Author", code = 204)
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@ApiParam("Author id number to delete") @PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @DeleteMapping("all-disable")
//    public ResponseEntity<?> deleteAllDisable() {
//        service.deleteAllDisableAutor();
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    private Page<AutorView> autorPageToAutorViewPage(Page<Autor> page) {
        List<AutorView> list = page.getContent().stream()
                .map(AutorView::new).collect(Collectors.toList());
        return new PageImpl<>(list);
    }
}
