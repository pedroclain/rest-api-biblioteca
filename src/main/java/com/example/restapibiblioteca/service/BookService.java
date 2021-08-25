package com.example.restapibiblioteca.service;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.dto.BookRequestCreate;
import com.example.restapibiblioteca.dto.BookRequestUpdate;
import com.example.restapibiblioteca.dto.BookView;
import com.example.restapibiblioteca.exception.ResourceNotFound;
import com.example.restapibiblioteca.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository repository;
    private final AutorService autorService;
    private final GenderService genderService;
    private final PublisherService publisherService;

    public BookService(BookRepository repository, AutorService autorService, GenderService genderService, PublisherService publisherService) {
        this.repository = repository;
        this.autorService = autorService;
        this.genderService = genderService;
        this.publisherService = publisherService;
    }

    public Book findByName(String name) {
        return find(name);
    }

    public Book findById(long id) {
        return find(id);
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

    @Transactional
    public Book save(BookRequestCreate bookRequest) {
        return repository.save(convertBookRequestCreateToBook(bookRequest));
    }

    @Transactional
    public Book update(BookRequestUpdate bookRequest) {
        delete(bookRequest.getId());
        return repository.save(convertBookRequestUpdateToBook(bookRequest));
    }

    public List<Book> listAll() {
        return repository.findAll();
    }

    public Page<Book> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    private Book convertBookRequestCreateToBook(BookRequestCreate requestCreate) {
        return getBook(requestCreate.getName(), requestCreate.getAutorId(), requestCreate.getGenderId(), requestCreate.getPublisherId(), requestCreate.getPublishDate());
    }

    private Book convertBookRequestUpdateToBook(BookRequestUpdate requestUpdate) {
        return getBook(requestUpdate.getName(), requestUpdate.getAutorId(), requestUpdate.getGenderId(), requestUpdate.getPubisherId(), requestUpdate.getPublishDate());
    }

    private Book getBook(String name, long autorId, long genderId, long pubisherId, LocalDate publishDate) {
        Book book = new Book();
        book.setName(name);
        book.setAutor(autorService.findById(autorId));
        book.setGender(genderService.findById(genderId));
        book.setPublisher(publisherService.findById(pubisherId));
        book.setPublishDate(publishDate);
        return book;
    }
}
