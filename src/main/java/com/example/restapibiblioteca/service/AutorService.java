package com.example.restapibiblioteca.service;

import com.example.restapibiblioteca.domain.Autor;
import com.example.restapibiblioteca.dto.AutorRequestCreate;
import com.example.restapibiblioteca.dto.AutorRequestUpdate;
import com.example.restapibiblioteca.dto.AutorView;
import com.example.restapibiblioteca.exception.ResourceNotFound;
import com.example.restapibiblioteca.repository.AutorRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public AutorView findByName(String name) {
        return new AutorView(find(name));
    }

    public AutorView findById(long id) {
        return new AutorView(find(id));
    }

    private Autor find(long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFound("Autor with id "+id+" not found"));
    }

    private Autor find(String name) {
        return repository.findByName(name).orElseThrow(() ->
                new ResourceNotFound("Autor "+name+" not found"));
    }

    @Transactional
    public void delete(long id) {
        repository.delete(find(id));
    }

    public AutorView save(AutorRequestCreate AutorRequest) {
        return new AutorView(repository.save(AutorRequest.getAutor()));
    }

    @Transactional
    public AutorView update(AutorRequestUpdate AutorRequest) {
        delete(AutorRequest.getId());
        return new AutorView(repository.save(AutorRequest.getAutor()));
    }

    public List<AutorView> list() {
        return repository.findAll().stream().map(AutorView::new).collect(Collectors.toList());
    }

    public List<AutorView> list(Pageable pageable) {
        return repository.findAll(pageable).get().map(AutorView::new)
                .collect(Collectors.toList());
    }
}
