package com.example.restapibiblioteca.api.v1;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.dto.request.BookRequestCreate;
import com.example.restapibiblioteca.dto.request.BookRequestUpdate;
import com.example.restapibiblioteca.dto.view.AutorView;
import com.example.restapibiblioteca.dto.view.BookView;
import com.example.restapibiblioteca.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api("Endpoint to manage Books")
@RestController
@RequestMapping("api/v1/book")
public class BookEndpoint{

    private final BookService service;

    public BookEndpoint(BookService service) {
        this.service = service;
    }

    @ApiOperation(value = "Return a list of all Books", response = BookView.class)
    @GetMapping("all")
    public ResponseEntity<?> listAll() {
        List<BookView> bookViewList = service.listAll().stream()
                .map(BookView::new).collect(Collectors.toList());
        return new ResponseEntity<>(bookViewList, HttpStatus.OK);
    }

    @ApiOperation(value = "Return all Books inside a pages", response = BookView.class)
    @GetMapping()
    public ResponseEntity<?> list(Pageable pageable) {
        return new ResponseEntity<>(bookPageToBookViewPage(service.list(pageable)),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Return a Book by id number", response = BookView.class)
    @GetMapping("find/id/{id}")
    public ResponseEntity<?> findById(@ApiParam("Book id number to find") @PathVariable Long id) {
        return new ResponseEntity<>(new BookView(service.findById(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "Return Books by name inside a page", response = BookView.class)
    @GetMapping("find/name/{name}")
    public ResponseEntity<?> findByName(@ApiParam("A part of book name") @PathVariable String name, Pageable pageable) {
        return new ResponseEntity<>(bookPageToBookViewPage(service.findByName(name, pageable)),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Return Books by Author name inside a page", response = BookView.class)
    @GetMapping("find/autor/{name}")
    public ResponseEntity<?> findByAutorName(@ApiParam("A part of author name to find") @PathVariable String name, Pageable pageable) {
        return new ResponseEntity<>(bookPageToBookViewPage(service.findByAutor(name, pageable)),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Return Books by Gender name inside a page", response = BookView.class)
    @GetMapping("find/gender/{name}")
    public ResponseEntity<?> findByGenderName(@ApiParam("A part of gender name to find") @PathVariable String name, Pageable pageable) {
        return new ResponseEntity<>(bookPageToBookViewPage(service.findByGender(name, pageable)),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Return Books by Publisher name inside a page", response = BookView.class)
    @GetMapping("find/publisher/{name}")
    public ResponseEntity<?> findByPublisherName(@ApiParam("A part of publisher name to find") @PathVariable String name, Pageable pageable) {
        return new ResponseEntity<>(bookPageToBookViewPage(service.findByPublisher(name, pageable)),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new Book", response = BookView.class, code = 201)
    @PostMapping
    public ResponseEntity<?> save(@ApiParam("Json request book body to save") @RequestBody @Valid BookRequestCreate bookRequest) {
        return new ResponseEntity<>(new BookView(service.save(bookRequest)), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a Author by id number", code = 204)
    @PutMapping
    public ResponseEntity<?> update(@ApiParam("Json request book body to update") @RequestBody @Valid BookRequestUpdate bookRequest) {
        service.update(bookRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Soft delete a Book", code = 204)
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@ApiParam("Book id number to delete") @PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @DeleteMapping("all-disable")
//    public ResponseEntity<?> deleteAllDisable() {
//        service.deleteAllDisableBooks();
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    private Page<BookView> bookPageToBookViewPage(Page<Book> page) {
        List<BookView> list = page.getContent().stream()
                .map(BookView::new).collect(Collectors.toList());
        return new PageImpl<>(list, page.getPageable(), page.getNumber());
    }
}
