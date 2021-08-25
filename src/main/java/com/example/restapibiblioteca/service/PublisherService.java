package com.example.restapibiblioteca.service;

import com.example.restapibiblioteca.domain.Publisher;
import com.example.restapibiblioteca.dto.PublisherRequestCreate;
import com.example.restapibiblioteca.dto.PublisherRequestUpdate;
import com.example.restapibiblioteca.exception.ResourceNotFound;
import com.example.restapibiblioteca.repository.PublisherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepository repository;

    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    public Publisher findByName(String name) {
        return find(name);
    }

    public Publisher findById(long id) {
        return find(id);
    }

    private Publisher find(long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFound("Publisher with id "+id+" not found"));
    }

    private Publisher find(String name) {
        return repository.findByName(name).orElseThrow(() ->
                new ResourceNotFound("Publisher "+name+" not found"));
    }

    @Transactional
    public void delete(long id) {
        repository.delete(find(id));
    }

    public Publisher save(PublisherRequestCreate genderRequest) {
        return repository.save(genderRequest.getPublisher());
    }

    @Transactional
    public Publisher update(PublisherRequestUpdate genderRequest) {
        delete(genderRequest.getId());
        return repository.save(genderRequest.getPublisher());
    }

    public List<Publisher> listAll() {
        return repository.findAll();
    }

    public Page<Publisher> list(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
