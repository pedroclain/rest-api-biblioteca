package com.example.restapibiblioteca.api.v1;

import com.example.restapibiblioteca.dto.BookRequestCreate;
import com.example.restapibiblioteca.dto.BookRequestUpdate;
import com.example.restapibiblioteca.dto.BookView;
import com.example.restapibiblioteca.service.BookService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/book")
public class BookEndpoint{

    private final BookService service;

    public BookEndpoint(BookService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        return new ResponseEntity<>(new BookView(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<?> find(@PathVariable String name) {
        return new ResponseEntity<>(new BookView(service.findByName(name)), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<?> listAll() {
        List<BookView> bookViewList = service.listAll().stream()
                .map(BookView::new).collect(Collectors.toList());
        return new ResponseEntity<>(bookViewList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> list(Pageable pageable) {
        List<BookView> bookViewList = service.list(pageable).stream()
                .map(BookView::new).collect(Collectors.toList());
        PageImpl<BookView> bookViewPage = new PageImpl<>(bookViewList, pageable, pageable.getPageSize());
        return new ResponseEntity<>(bookViewPage, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody BookRequestCreate bookRequest) {
        return new ResponseEntity<>(new BookView(service.save(bookRequest)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody BookRequestUpdate bookRequest) {
        return new ResponseEntity<>(new BookView(service.update(bookRequest)), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
