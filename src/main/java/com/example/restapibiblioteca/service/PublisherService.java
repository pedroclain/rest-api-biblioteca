package com.example.restapibiblioteca.service;

import com.example.restapibiblioteca.domain.Publisher;
import com.example.restapibiblioteca.dto.request.PublisherRequestCreate;
import com.example.restapibiblioteca.dto.request.PublisherRequestUpdate;
import com.example.restapibiblioteca.exception.ResourceNotFound;
import com.example.restapibiblioteca.repository.BookRepository;
import com.example.restapibiblioteca.repository.PublisherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    public PublisherService(PublisherRepository publisherRepository, BookRepository bookRepository) {
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
    }

    public List<Publisher> listAll() {
        return publisherRepository.findAll();
    }

    public Page<Publisher> list(Pageable pageable) {
        return publisherRepository.findAll(pageable);
    }

    public Page<Publisher> findByName(String name, Pageable pageable) {
        return publisherRepository.findByName(name, pageable);
    }


    public Publisher findById(long id) {
        return publisherRepository.findById(id).orElseThrow(() ->
                new ResourceNotFound(String.format("Publisher with id %d not found", id)));
    }
    @Transactional
    public void delete(long id) {
        bookRepository.deleteAllBooksRelatedToPublisher(id);
        publisherRepository.delete(id);
    }

//    @Transactional
//    public void deleteAllDisablePublisher() {
//        List<Publisher> disableList = publisherRepository.findAllDisablePublisher();
//        disableList.forEach((p) ->
//                bookRepository.deleteAllDisableBooksRelatedToPublisher(p.getId()));
//        publisherRepository.deleteAllDisablePublisher();
//    }

    public Publisher save(PublisherRequestCreate genderRequest) {
        return publisherRepository.save(genderRequest.getPublisher());
    }

    @Transactional
    public Publisher update(PublisherRequestUpdate genderRequest) {
        delete(genderRequest.getId());
        return publisherRepository.save(genderRequest.getPublisher());
    }
}
