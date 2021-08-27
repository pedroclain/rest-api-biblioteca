package com.example.restapibiblioteca.api.v1;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.dto.BookRequestCreate;
import com.example.restapibiblioteca.dto.BookRequestUpdate;
import com.example.restapibiblioteca.dto.BookView;
import com.example.restapibiblioteca.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/book")
public class BookEndpoint{

    private final BookService service;

    public BookEndpoint(BookService service) {
        this.service = service;
    }

    // TODO mudar lugar
    private Page<BookView> bookPageToBookViewPage(Page<Book> page) {
        List<BookView> list = page.getContent().stream()
                .map(BookView::new).collect(Collectors.toList());
        return new PageImpl<>(list, page.getPageable(), page.getPageable().getPageSize());
    }

    @GetMapping("find/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(new BookView(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping("find/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name, Pageable pageable) {
        return new ResponseEntity<>(bookPageToBookViewPage(service.findByName(name, pageable)),
                HttpStatus.OK);
    }

    @GetMapping("find/autor/{name}")
    public ResponseEntity<?> findByAutorName(@PathVariable String name, Pageable pageable) {
        return new ResponseEntity<>(bookPageToBookViewPage(service.findByAutor(name, pageable)),
                HttpStatus.OK);
    }

    @GetMapping("find/gender/{name}")
    public ResponseEntity<?> findByGenderName(@PathVariable String name, Pageable pageable) {
        return new ResponseEntity<>(bookPageToBookViewPage(service.findByGender(name, pageable)),
                HttpStatus.OK);
    }

    @GetMapping("find/publisher/{name}")
    public ResponseEntity<?> findByPublisherName(@PathVariable String name, Pageable pageable) {
        return new ResponseEntity<>(bookPageToBookViewPage(service.findByPublisher(name, pageable)),
                HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(bookPageToBookViewPage(service.listAll(pageable)),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid BookRequestCreate bookRequest) {
        return new ResponseEntity<>(new BookView(service.save(bookRequest)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid BookRequestUpdate bookRequest) {
        return new ResponseEntity<>(new BookView(service.update(bookRequest)), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
