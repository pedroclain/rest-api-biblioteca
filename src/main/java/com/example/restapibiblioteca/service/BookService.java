package com.example.restapibiblioteca.service;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.dto.BookRequestCreate;
import com.example.restapibiblioteca.dto.BookRequestUpdate;
import com.example.restapibiblioteca.dto.BookView;
import com.example.restapibiblioteca.exception.ResourceNotFound;
import com.example.restapibiblioteca.repository.BookRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public BookView findByName(String name) {
        return new BookView(find(name));
    }

    public BookView findById(long id) {
        return new BookView(find(id));
    }

    private Book find(long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFound("Book with id "+id+" not found"));
    }

    private Book find(String name) {
        return repository.findByName(name).orElseThrow(() ->
                new ResourceNotFound("Book "+name+" not found"));
    }

    @Transactional
    public void delete(long id) {
        repository.delete(find(id));
    }

    public BookView save(BookRequestCreate BookRequest) {
        return new BookView(repository.save(BookRequest.getBook()));
    }

    @Transactional
    public BookView update(BookRequestUpdate BookRequest) {
        delete(BookRequest.getId());
        return new BookView(repository.save(BookRequest.getBook()));
    }

    public List<BookView> list() {
        return repository.findAll().stream().map(BookView::new).collect(Collectors.toList());
    }

    public List<BookView> list(Pageable pageable) {
        return repository.findAll(pageable).get().map(BookView::new)
                .collect(Collectors.toList());
    }
}
