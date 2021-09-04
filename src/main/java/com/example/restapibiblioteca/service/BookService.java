package com.example.restapibiblioteca.service;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.dto.request.BookRequestCreate;
import com.example.restapibiblioteca.dto.request.BookRequestUpdate;
import com.example.restapibiblioteca.exception.ResourceNotFound;
import com.example.restapibiblioteca.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

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

    public List<Book> listAll() {
        return repository.findAll();
    }

    public Page<Book> list(Pageable pageable) {
        return repository.findAll(pageable);
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

    public void delete(long id) {
        repository.delete(id);
    }

//    public void deleteAllDisableBooks() {
//        repository.deleteAllDisableBooks();
//    }

    @Transactional
    public Book save(BookRequestCreate bookRequest) {
        return repository.save(convertBookRequestCreateToBook(bookRequest));
    }

    @Transactional
    public void update(BookRequestUpdate bookRequest) {
        Book book = convertBookRequestUpdateToBook(bookRequest);
        System.out.println(book.getName());
        repository.update(book.getId(), book.getName(), book.getGender(),
                book.getAutor(), book.getPublisher(), book.getPublishDate());
    }

    private Book convertBookRequestCreateToBook(BookRequestCreate requestCreate) {
        return toBook(0L, requestCreate.getName(), requestCreate.getAutorId(), requestCreate.getGenderId(), requestCreate.getPublisherId(), requestCreate.getPublishDate());
    }

    private Book convertBookRequestUpdateToBook(BookRequestUpdate requestUpdate) {
        return toBook(requestUpdate.getId(), requestUpdate.getName(), requestUpdate.getAutorId(), requestUpdate.getGenderId(), requestUpdate.getPublisherId(), requestUpdate.getPublishDate());
    }

    private Book toBook(Long id, String name, long autorId, long genderId, long pubisherId, LocalDate publishDate) {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAutor(autorService.findById(autorId));
        book.setGender(genderService.findById(genderId));
        book.setPublisher(publisherService.findById(pubisherId));
        book.setPublishDate(publishDate);
        return book;
    }
}
