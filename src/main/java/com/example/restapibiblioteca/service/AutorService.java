package com.example.restapibiblioteca.service;

import com.example.restapibiblioteca.domain.Autor;
import com.example.restapibiblioteca.dto.AutorRequestCreate;
import com.example.restapibiblioteca.dto.AutorRequestUpdate;
import com.example.restapibiblioteca.exception.ResourceNotFound;
import com.example.restapibiblioteca.repository.AutorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public Autor findByName(String name) {
        return find(name);
    }

    public Autor findById(long id) {
        return find(id);
    }

    private Autor find(long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFound(String.format("Autor with id %d not found", id)));
    }

    private Autor find(String name) {
        return repository.findByName(name).orElseThrow(() ->
                new ResourceNotFound(String.format("Autor %s not found", name)));
    }

    @Transactional
    public void delete(long id) {
        repository.delete(find(id));
    }

    public Autor save(AutorRequestCreate genderRequest) {
        return repository.save(genderRequest.getAutor());
    }

    @Transactional
    public Autor update(AutorRequestUpdate genderRequest) {
        delete(genderRequest.getId());
        return repository.save(genderRequest.getAutor());
    }

    public List<Autor> listAll() {
        return repository.findAll();
    }

    public Page<Autor> list(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
