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
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    public Page<Book> findByName(String name, Pageable pageable) {
        return repository.findByName(name, pageable);
    }

    public Page<Book> findByGender(String genderName, Pageable pageable) {
        return repository.findByGenderName(genderName, pageable);
    }

    public Page<Book> findByAutor(String autorName, Pageable pageable) {
        return repository.findByAutorName(autorName, pageable);
    }

    public Page<Book> findByPublisher(String publisherName, Pageable pageable) {
        return repository.findByPublisherName(publisherName, pageable);
    }

    public Book findById(long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFound(String.format("Book with id %d not found", id)));
    }

    @Transactional
    public void delete(long id) {
        repository.delete(findById(id));
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

    public Page<Book> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Book> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    private Book convertBookRequestCreateToBook(BookRequestCreate requestCreate) {
        return getBook(requestCreate.getName(), requestCreate.getAutorId(), requestCreate.getGenderId(), requestCreate.getPublisherId(), requestCreate.getPublishDate());
    }

    private Book convertBookRequestUpdateToBook(BookRequestUpdate requestUpdate) {
        return getBook(requestUpdate.getName(), requestUpdate.getAutorId(), requestUpdate.getGenderId(), requestUpdate.getPublisherId(), requestUpdate.getPublishDate());
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
